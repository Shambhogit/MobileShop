package com.example.practial_25;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnScale, btnRotate, btnAlpha, btnTranslate;
    ImageView imgAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScale = findViewById(R.id.btnScale);
        btnRotate = findViewById(R.id.btnRotate);
        btnAlpha = findViewById(R.id.btnAlpha);
        btnTranslate = findViewById(R.id.btnTranslate);
        imgAnim = findViewById(R.id.imgAnim);

        Animation move = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);
        Animation rotate = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
        Animation scale = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);
        Animation alpha = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha);

        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgAnim.startAnimation(scale);
            }
        });

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgAnim.startAnimation(move);
            }
        });

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgAnim.startAnimation(rotate);
            }
        });

        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgAnim.startAnimation(alpha);
            }
        });
    }
}