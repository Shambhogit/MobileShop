package com.example.practical_20;

import android.app.Service;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class WifiService extends Service {


    @Override
    public void onCreate() {
            super.onCreate();
        Toast.makeText(this, "Is created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "is stated", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "is Destroyed", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
