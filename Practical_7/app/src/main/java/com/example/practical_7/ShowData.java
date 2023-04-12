package com.example.practical_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowData extends AppCompatActivity {


    TextView tvName, tvRoll, tvPhone, tvClass, tvAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        tvName = findViewById(R.id.tvName);
        tvRoll = findViewById(R.id.tvRollNumber);
        tvPhone = findViewById(R.id.tvPhone);
        tvClass = findViewById(R.id.tvClass);
        tvAge = findViewById(R.id.tvAge);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String roll = i.getStringExtra("roll");
        String sclass = i.getStringExtra("class");
        String age = i.getStringExtra("age");
        String phone = i.getStringExtra("phone");

        tvName.setText("Name : "+name);
        tvRoll.setText("Roll No : "+roll);
        tvPhone.setText("Phone No : "+phone);
        tvAge.setText("Age : "+age);
        tvClass.setText("Class : "+sclass);

    }
}