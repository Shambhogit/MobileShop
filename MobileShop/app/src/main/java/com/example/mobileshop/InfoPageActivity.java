package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileshop.adapters.ListAdapter;
import com.example.mobileshop.models.InfoModel;
import com.example.mobileshop.models.MobileModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class InfoPageActivity extends AppCompatActivity {

    ArrayList<InfoModel> infoModels = new ArrayList<>();
    MobileModel mobile;
    ListView lvInfo;

    ImageView ivImage;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    Button btnBuy, btnAddToCart;
    TextView tvName, tvPrice;
    ListAdapter listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);
        getSupportActionBar().hide();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        auth = FirebaseAuth.getInstance();

        lvInfo = findViewById(R.id.lvInfo);
        tvName = findViewById(R.id.tvName);
        tvPrice =findViewById(R.id.tvPrice);
        ivImage = findViewById(R.id.ivImage);
        btnBuy = findViewById(R.id.btnBuy);
        btnAddToCart =findViewById(R.id.btnCart);

        listAdapter = new ListAdapter(InfoPageActivity.this,infoModels);
        lvInfo.setAdapter(listAdapter);

        Intent i = getIntent();
        mobile =(MobileModel) i.getSerializableExtra("data");
        setDataToInfo();

        Picasso.with(this).load(mobile.getMobileImageURI()).into(ivImage);
        tvName.setText(mobile.getName());
        tvPrice.setText(String.format(Locale.US,mobile.getPrice()+" ₹"));

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(InfoPageActivity.this);
                progressDialog.setTitle("Adding To Cart...");
                progressDialog.show();
                String id = databaseReference.push().getKey();
                MobileModel model = new MobileModel(id,mobile.getMobileImageURI(),mobile.getName(), mobile.getPrice(), mobile.getRamRom(), mobile.getProcessor(), mobile.getCamera(), mobile.getDisplay(), mobile.getBattery(),mobile.getWarranty());
                databaseReference.child(auth.getCurrentUser().getUid()).child("userCart").child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                            Toast.makeText(InfoPageActivity.this,"Product is Added to Cart",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InfoPageActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InfoPageActivity.this,BuyNowActivity.class);
                i.putExtra("data",mobile);
                startActivity(i);
            }
        });

    }

    public void setDataToInfo(){
        infoModels.add(new InfoModel("RAM | ROM",mobile.getRamRom()));
        infoModels.add(new InfoModel("Processor",mobile.getProcessor()));
        infoModels.add(new InfoModel("Camera",mobile.getCamera()));
        infoModels.add(new InfoModel("Display",mobile.getDisplay()));
        infoModels.add(new InfoModel("Battery",mobile.getBattery()));
        infoModels.add(new InfoModel("Warranty",mobile.getWarranty()));
    }
}