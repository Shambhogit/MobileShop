package com.example.practical_21;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){
        if(Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())){
            Toast.makeText(context, "Ari plane mode changed", Toast.LENGTH_SHORT).show();
        }
        else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            Toast.makeText(context, "Network connection changed", Toast.LENGTH_SHORT).show();
        }
        else if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())){
            Toast.makeText(context, "Charging connected", Toast.LENGTH_SHORT).show();
        }
        else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())){
            Toast.makeText(context, "Charging disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}
