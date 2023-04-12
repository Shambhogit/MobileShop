package com.example.practical_9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton tbMode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbMode = findViewById(R.id.tbMode);

        tbMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tbMode.isActivated()){
                    Toast.makeText(MainActivity.this, ""+tbMode.getText(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, ""+tbMode.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}