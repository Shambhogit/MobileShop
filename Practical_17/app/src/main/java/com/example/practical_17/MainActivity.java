package com.example.practical_17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("lifeCycle","onCreate() is called");
    }
    protected void onStart(){
        super.onStart();
        Log.d("lifeCycle","onStart() is Called");
    }

    protected void onResume(){
        super.onResume();
        Log.d("lifeCycle","onResume() is called");
    }

    protected void onRestart(){
        super.onRestart();
        Log.d("lifeCycle","onRestart() is called");
    }

    protected void onPause(){
        super.onPause();
        Log.d("lifeCycle","onPause() is called");
    }

    protected void onStop(){
        super.onStop();
        Log.d("lifeCycle","onStop() is called");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d("lifeCycle","onDestroy() is called");
    }
}