package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileshop.adapters.CartItemAdapter;
import com.example.mobileshop.models.MobileModel;
import com.example.mobileshop.models.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    Button btnPlaceOrder;
    TextView tvTotal;
    ListView lvCartItem;
    DatabaseReference databaseReference;
    ArrayList<MobileModel> mobiles = new ArrayList<>();
    FirebaseAuth auth;
    UserInfo currentUser;
    CartItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        tvTotal = findViewById(R.id.tvPrice);
        lvCartItem = findViewById(R.id.lvCartItem);
        getSupportActionBar().hide();
        currentUser = (UserInfo) getIntent().getSerializableExtra("data");

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(auth.getCurrentUser().getUid()).child("userCart");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mobiles.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MobileModel mobileModel = dataSnapshot.getValue(MobileModel.class);
                    mobiles.add(mobileModel);
                }
                if(mobiles.size() == 0){
                    Toast.makeText(CartActivity.this, "No Product in the Cart", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    adapter = new CartItemAdapter(CartActivity.this,mobiles);
                    lvCartItem.setAdapter(adapter);
                    int sum = 0;
                    for (int i = 0 ;i<mobiles.size();i++){
                        sum = sum + Integer.parseInt(mobiles.get(i).getPrice());
                    }
                    tvTotal.setText(String.format(Locale.US,""+sum+" ₹"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iOrder = new Intent(CartActivity.this,CartPlaceOrderActivity.class);
                iOrder.putExtra("data",currentUser);
                iOrder.putExtra("totalPrice",tvTotal.getText().toString());
                startActivity(iOrder);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}