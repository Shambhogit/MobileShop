package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileshop.models.BuyModel;
import com.example.mobileshop.models.InfoModel;
import com.example.mobileshop.models.MobileModel;
import com.example.mobileshop.models.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class BuyNowActivity extends AppCompatActivity {

    ImageView ivImage;
    TextView tvName, tvPrice, tvTotal;
    EditText edtQuantity, edtMobileNo, edtCustomerName,edtAddress;
    Spinner spPayOption;
    Button btnConfirmOrder;
    MobileModel mobile;
    FirebaseAuth auth;
    DatabaseReference databaseReference;


    String [] option = {
            "Credit OR Debit Card","Paytm","Google Pay","Cash On Delivery","Pay With E-Banking"
    };

    ArrayAdapter<String> adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);

        auth = FirebaseAuth.getInstance();
        Intent i = getIntent();
        mobile =(MobileModel) i.getSerializableExtra("data");

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        ivImage = findViewById(R.id.ivImage);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        tvTotal = findViewById(R.id.tvTotal);
        edtQuantity = findViewById(R.id.edtQuantity);
        edtCustomerName = findViewById(R.id.edtCustomerName);
        edtAddress = findViewById(R.id.edtAddress);
        edtMobileNo = findViewById(R.id.edtMobileNo);
        spPayOption = findViewById(R.id.spPayOption);
        btnConfirmOrder = findViewById(R.id.btnConfirm);

        getSupportActionBar().hide();

        tvName.setText(mobile.getName());
        tvPrice.setText(String.format(Locale.US,mobile.getPrice()+" ₹"));
        Picasso.with(BuyNowActivity.this).load(mobile.getMobileImageURI()).into(ivImage);
        tvTotal.setText(String.format(Locale.US,mobile.getPrice()+" ₹"));

        adapter = new ArrayAdapter<>(BuyNowActivity.this, android.R.layout.simple_list_item_1,option);
        spPayOption.setAdapter(adapter);

        setData();
        edtQuantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if(edtQuantity.getText().toString().isEmpty() || edtQuantity.getText().toString().equals("0")){
                    tvTotal.setText("null");
                }
                else{
                    int total = Integer.parseInt(edtQuantity.getText().toString()) * Integer.parseInt(mobile.getPrice());
                    tvTotal.setText(total+" ₹");
                }
                return false;
            }
        });

        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToServer();
                finish();
            }
        });
    }

    private void saveToServer(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Order Placing please Wait...");
        progressDialog.show();
        String id = databaseReference.push().getKey();

        if(edtQuantity.getText().toString().equals("0") || edtAddress.getText().toString().isEmpty()|| edtQuantity.getText().toString().isEmpty() || edtCustomerName.getText().toString().isEmpty() || edtMobileNo.getText().toString().isEmpty()){
            Toast.makeText(this, "Please Fill all the fields", Toast.LENGTH_SHORT).show();
        }else {
            BuyModel buyModel = new BuyModel(mobile.getMobileImageURI(),mobile.getName(),mobile.getPrice(),edtQuantity.getText().toString(),spPayOption.getSelectedItem().toString(),edtCustomerName.getText().toString(),edtMobileNo.getText().toString(),edtAddress.getText().toString(),tvTotal.getText().toString());
            databaseReference.child(auth.getCurrentUser().getUid()).child("orderHistory").child(id).setValue(buyModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    cleareData();
                    Toast.makeText(BuyNowActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Toast.makeText(BuyNowActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void cleareData(){
        edtAddress.setText("");
        edtMobileNo.setText("");
        edtQuantity.setText("1");
        edtCustomerName.setText("");
        spPayOption.setSelection(0);
    }

    public void setData(){
        databaseReference.child(auth.getCurrentUser().getUid()).child("info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserInfo userInfo = (UserInfo) snapshot.getValue(UserInfo.class);
                edtAddress.setText(userInfo.getAddress());
                edtCustomerName.setText(userInfo.getName());
                edtMobileNo.setText(userInfo.getPhone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(BuyNowActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}