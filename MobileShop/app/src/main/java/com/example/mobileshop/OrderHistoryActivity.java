package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobileshop.adapters.HistoryAdapter;
import com.example.mobileshop.models.BuyModel;
import com.example.mobileshop.models.CartOrderHistory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    ArrayList<CartOrderHistory> history = new ArrayList<>();
    RecyclerView lvOrderHistory;
    ArrayList<BuyModel> buyModels = new ArrayList<>();
    HistoryAdapter adapter;

    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        lvOrderHistory = findViewById(R.id.lvOrderHistory);
        lvOrderHistory.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();

        databaseReference.child(auth.getCurrentUser().getUid()).child("orderHistory").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    BuyModel model = dataSnapshot.getValue(BuyModel.class);
                    buyModels.add(model);
                }
                history.add(new CartOrderHistory(buyModels));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrderHistoryActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child(auth.getCurrentUser().getUid()).child("cartOrderHistory").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    history.add(dataSnapshot.getValue(CartOrderHistory.class));
                }

                if(history.size()==0){
                    Toast.makeText(OrderHistoryActivity.this, "No order History", Toast.LENGTH_SHORT).show();
                }
                else {
                    adapter = new HistoryAdapter(OrderHistoryActivity.this,history);
                    lvOrderHistory.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}