package com.example.practical_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox oop,dsu,dbms,java,osy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oop = findViewById(R.id.cbOOP);
        dsu = findViewById(R.id.cbDSU);
        dbms = findViewById(R.id.cbDBMS);
        osy = findViewById(R.id.cbOSY);
        java = findViewById(R.id.cbJAVA);

        oop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "OOP is selected", Toast.LENGTH_SHORT).show();
            }
        });
        dsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "DSU is selected", Toast.LENGTH_SHORT).show();
            }
        });
        dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "DBMS is selected", Toast.LENGTH_SHORT).show();
            }
        });
        osy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "OSY is selected", Toast.LENGTH_SHORT).show();
            }
        });
        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "JAVA is selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

}