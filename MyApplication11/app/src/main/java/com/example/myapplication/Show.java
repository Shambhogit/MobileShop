package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Show extends AppCompatActivity {

    TextView tvName, tvRoll, tvAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        tvName = findViewById(R.id.tvName);
        tvRoll = findViewById(R.id.tvRoll);
        tvAge = findViewById(R.id.tvAge);

        Intent i = getIntent();

        tvName.setText(i.getStringExtra("name"));
        tvRoll.setText(i.getStringExtra("roll"));
        tvAge.setText(i.getStringExtra("age"));
    }
}