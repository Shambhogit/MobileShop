package com.example.customerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {

    ListView lvData;
    ArrayList<CustomerModule> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        lvData = findViewById(R.id.lvData);
        CustomerDatabase db = new CustomerDatabase(ShowData.this);
        data = db.getAllData();
        CustomerAdapter adapter = new CustomerAdapter(ShowData.this,data);

        lvData.setAdapter(adapter);
    }
}