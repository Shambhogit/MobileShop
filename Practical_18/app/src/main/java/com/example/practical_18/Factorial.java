package com.example.practical_18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Factorial extends AppCompatActivity {

    TextView tvFactorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial);

        tvFactorial = findViewById(R.id.tvFactorial);
        Intent i = getIntent();
        String factorial = Integer.toString(i.getIntExtra("factorial",0));
        tvFactorial.setText(factorial);
    }
}