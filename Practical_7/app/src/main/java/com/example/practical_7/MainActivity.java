package com.example.practical_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtRoll, edtAge, edtPhone, edtClass;
    Button btnSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtRoll = findViewById(R.id.edtRoll);
        edtAge = findViewById(R.id.edtAge);
        edtPhone = findViewById(R.id.edtPhone);
        edtClass = findViewById(R.id.edtClass);

        btnSaveData = findViewById(R.id.btnSaveData);

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showData = new Intent(MainActivity.this,ShowData.class);
                showData.putExtra("name",edtName.getText().toString());
                showData.putExtra("roll",edtRoll.getText().toString());
                showData.putExtra("age",edtAge.getText().toString());
                showData.putExtra("phone",edtPhone.getText().toString());
                showData.putExtra("class",edtClass.getText().toString());
                startActivity(showData);
            }
        });
    }
}