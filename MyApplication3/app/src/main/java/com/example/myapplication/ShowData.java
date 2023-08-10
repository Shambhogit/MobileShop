package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {

    ListView lvShow;
    ArrayList<String> names = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        lvShow = findViewById(R.id.lvShow);
        StudentDatabase db = new StudentDatabase(ShowData.this);
        names = db.getNames();

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,names);
        lvShow.setAdapter(adp);
    }
}