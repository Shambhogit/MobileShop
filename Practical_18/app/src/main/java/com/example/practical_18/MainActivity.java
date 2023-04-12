package com.example.practical_18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnShowFactorial, btnStartDialer;
    EditText edtFactorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowFactorial = findViewById(R.id.btnShowFactorial);
        btnStartDialer = findViewById(R.id.btnStartDialer);
        edtFactorial = findViewById(R.id.edtFactorial);

        btnStartDialer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent dialer = new Intent(Intent.ACTION_DIAL);
                startActivity(dialer);
            }
        });

        btnShowFactorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facto = new Intent(MainActivity.this,Factorial.class);
                int number = Integer.parseInt(edtFactorial.getText().toString());
                int factorial = 1;
                for (int i = number;i>0;i--){
                    factorial = factorial * i;
                }
                facto.putExtra("factorial",factorial);
                startActivity(facto);
            }
        });
    }
}