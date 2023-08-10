package com.example.firebaseimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    CardView cvAddItem, cvEditItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        cvAddItem = findViewById(R.id.cvAddItem);
        cvEditItem = findViewById(R.id.cvShowItems);

        cvAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, AddNewMobileData.class);
                startActivity(intent);
            }
        });

        cvEditItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, ShowDataActivity.class);
                startActivity(intent);
            }
        });
    }
}