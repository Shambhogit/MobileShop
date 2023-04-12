package com.example.practical_24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnEn;
    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEn = findViewById(R.id.btnEn);

        btnEn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                if(btAdapter.isEnabled()){
                    Toast.makeText(MainActivity.this, "BlueTooth is already Enable", Toast.LENGTH_SHORT).show();
                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{BLUETOOTH_SERVICE},1);
                    Intent newIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(newIntent);

                }
            }
        });
    }
}