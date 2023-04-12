package com.example.practical_23;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnOpenCamera;
    ImageView imgCaptureImage;

    private static final int REQUEST_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenCamera = findViewById(R.id.btnOpenCamera);
        imgCaptureImage = findViewById(R.id.imgCaptureImage);

        btnOpenCamera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent opneCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(opneCamera, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap image = (Bitmap) data.getExtras().get("data");
        imgCaptureImage.setImageBitmap(image);
    }
}