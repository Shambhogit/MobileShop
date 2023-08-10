package com.example.firebaseimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.firebaseimage.adapters.DataAdapter;
import com.example.firebaseimage.models.MobileModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    RecyclerView rvEditData;
    DataAdapter dataAdapter;
    ArrayList<MobileModel> mobileModels = new ArrayList<>();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        databaseReference = FirebaseDatabase.getInstance().getReference("myUploads");

        rvEditData = findViewById(R.id.rvShowData);
        rvEditData.setLayoutManager(new LinearLayoutManager(this));
        getDataFromServer();
    }

    private void getDataFromServer(){


        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mobileModels.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    mobileModels.add((MobileModel) dataSnapshot.getValue(MobileModel.class));
                }
                dataAdapter = new DataAdapter(ShowDataActivity.this,mobileModels);
                rvEditData.setAdapter(dataAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShowDataActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}