package com.example.practical14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvContacts;

    ArrayList<String> subjects = new ArrayList<>();
    GridView gvNames;
    ArrayList<String> names = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names.add("Shambho");
        names.add("Vishanvi");
        names.add("Sakshi");
        names.add("Aarti");
        names.add("Shital");
        names.add("Omkar");
        names.add("Ganesh");
        names.add("Kisan");
        names.add("Naman");
        names.add("Ruturaj");
        names.add("Vishal");
        names.add("Vijay");
        names.add("Atharv");
        names.add("Ajinkya");
        names.add("Vijaya");
        names.add("Prerana");
        names.add("Sneha");
        names.add("Yash");
        names.add("Chaitanya");
        names.add("Swami");
        names.add("Ashitosh");
        names.add("Tuakarm");

        subjects.add("Hindi");
        subjects.add("Marathi");
        subjects.add("English");
        subjects.add("Maths");
        subjects.add("History");
        subjects.add("Geography");
        subjects.add("Economics");
        subjects.add("Accounting");
        subjects.add("Information technology");
        subjects.add("Physics");
        subjects.add("Chemistry");
        subjects.add("Zoology");
        subjects.add("Biology");
        subjects.add("Horticulture");
        subjects.add("Entomalogy");
        subjects.add("Sericulture");

        lvContacts = findViewById(R.id.lvContacts);
        ArrayAdapter<String> conAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.listview,subjects);
        lvContacts.setAdapter(conAdapter);

        gvNames = findViewById(R.id.gvNames);
        ArrayAdapter<String> nameAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.listview,names);
        gvNames.setAdapter(nameAdapter);
    }
}