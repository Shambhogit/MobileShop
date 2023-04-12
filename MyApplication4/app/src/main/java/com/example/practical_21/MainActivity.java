package com.example.practical_21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter ariPlaneMode = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        IntentFilter connectivity = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        IntentFilter cConnected = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        IntentFilter cDisconnected = new IntentFilter(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(myBroadcastReceiver,ariPlaneMode);
        registerReceiver(myBroadcastReceiver,connectivity);
        registerReceiver(myBroadcastReceiver,cConnected);
        registerReceiver(myBroadcastReceiver,cDisconnected);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }
}