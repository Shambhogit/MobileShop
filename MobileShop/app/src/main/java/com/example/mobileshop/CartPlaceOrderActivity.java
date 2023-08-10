package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileshop.adapters.RVCartAdapter;
import com.example.mobileshop.models.BuyModel;
import com.example.mobileshop.models.CartOrderHistory;
import com.example.mobileshop.models.MobileModel;
import com.example.mobileshop.models.UserInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartPlaceOrderActivity extends AppCompatActivity {

    RecyclerView rvCartPlaceOrderItems;
    ArrayList<MobileModel> cartMobiles = new ArrayList<>();
    UserInfo currentUser;
    DatabaseReference databaseReference;
    RVCartAdapter adapter;
    String totalPrice;
    FirebaseAuth auth;
    Spinner spOption;
    ArrayAdapter<String> spAdapter;
    Button btnPlaceOrder;
    ArrayList<BuyModel> buyModels = new ArrayList<>();
    EditText edtCustomerName, edtMobile, edtAddress;

    TextView tvTotal;
    String [] option = {
            "Credit OR Debit Card","Paytm","Google Pay","Cash On Delivery","Pay With E-Banking"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_place_order);
        getSupportActionBar().hide();

        btnPlaceOrder = findViewById(R.id.btnConfirm);
        edtAddress = findViewById(R.id.edtAddress);
        edtMobile = findViewById(R.id.edtMobileNo);
        tvTotal = findViewById(R.id.tvTotal);
        edtCustomerName = findViewById(R.id.edtCustomerName);
        spOption =findViewById(R.id.spPayOption);

        rvCartPlaceOrderItems = findViewById(R.id.rvCartShowItem);
        rvCartPlaceOrderItems.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        spAdapter = new ArrayAdapter<>(CartPlaceOrderActivity.this, android.R.layout.simple_list_item_1,option);
        spOption.setAdapter(spAdapter);


        currentUser = (UserInfo) getIntent().getSerializableExtra("data");
        totalPrice = getIntent().getExtras().getString("totalPrice");

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        edtCustomerName.setText(currentUser.getName());
        edtAddress.setText(currentUser.getAddress());
        edtMobile.setText(currentUser.getPhone());
        tvTotal.setText(totalPrice);

        setRvCartPlaceOrderItems();

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(MobileModel mobileModel : cartMobiles){
//                        public BuyModel(String image, String name, String price, String quantity, String paymentOption, String customer_name, String mobile_no,String address, String total_price)
                        buyModels.add(new BuyModel(mobileModel.getMobileImageURI(),mobileModel.getName(), mobileModel.getPrice(),"1",spOption.getSelectedItem().toString(), edtCustomerName.getText().toString(),edtMobile.getText().toString(), edtAddress.getText().toString(),""));
                }
                String id = databaseReference.push().getKey();
                databaseReference.child(auth.getCurrentUser().getUid()).child("cartOrderHistory").child(id).setValue(new CartOrderHistory(buyModels)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CartPlaceOrderActivity.this, "Data is Added", Toast.LENGTH_SHORT).show();
                    }
                });

                databaseReference.child(auth.getCurrentUser().getUid()).child("userCart").setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CartPlaceOrderActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CartPlaceOrderActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setRvCartPlaceOrderItems(){
        cartMobiles.clear();
        databaseReference.child(auth.getCurrentUser().getUid()).child("userCart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MobileModel mobileModel = dataSnapshot.getValue(MobileModel.class);
                    cartMobiles.add(mobileModel);
                }
                adapter = new RVCartAdapter(CartPlaceOrderActivity.this,cartMobiles);
                rvCartPlaceOrderItems.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CartPlaceOrderActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}