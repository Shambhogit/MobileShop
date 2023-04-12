package com.example.practical_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView actvSubjects;
    String []subjects = {"Marathi","hindi","maths","Giography","history","english",
    "Operating system","Mobile application development","Network information security","Python",
    "C++","programming with C","VB.NET","Biology","zoology","botany","java","java Script","DBMS"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actvSubjects = findViewById(R.id.actvSubject);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.select_dialog_item,subjects);
        actvSubjects.setThreshold(1);
        actvSubjects.setAdapter(adapter);
    }
}