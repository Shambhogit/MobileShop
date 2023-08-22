package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class PlayMusic extends AppCompatActivity {

    TextView tvName;
    ImageView ivSongImg;

    Handler handler;


    LottieAnimationView lottieAnimationView;
    ImageButton btnStart, btnNext, btnBefore;

    MediaPlayer mp;

    int index = 0;
    int count = 0;
    int progress = 0;


    ArrayList<Integer> images;
    ArrayList<Integer> paths;
    ArrayList<String> names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        tvName = findViewById(R.id.tvName);
        ivSongImg = findViewById(R.id.ivSongImg);
        btnStart = findViewById(R.id.btnStart);
        btnNext = findViewById(R.id.btnNext);
        btnBefore = findViewById(R.id.btnBefore);

        handler = new Handler();

        lottieAnimationView = findViewById(R.id.lav);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        index = i.getIntExtra("index", 0);

        images = i.getIntegerArrayListExtra("images");
        paths = i.getIntegerArrayListExtra("paths");
        names = i.getStringArrayListExtra("names");

        loadSong(index);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                btnStart.setBackgroundResource(R.drawable.play);
                lottieAnimationView.resumeAnimation();
                count = 0;

                lottieAnimationView.pauseAnimation();

                index++;
                if (index > names.size() - 1) {
                    index = 0;
                    loadSong(index);
                } else {
                    loadSong(index);
                }
            }
        });
        btnBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
                btnStart.setBackgroundResource(R.drawable.play);
                lottieAnimationView.resumeAnimation();
                count = 0;
                lottieAnimationView.pauseAnimation();

                index--;
                if (index < 0) {
                    index = names.size() - 1;
                    loadSong(index);
                } else {
                    loadSong(index);
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0) {
                    btnStart.setBackgroundResource(R.drawable.pause);
                    lottieAnimationView.playAnimation();
                    count++;
                    mp.start();

                } else {
                    btnStart.setBackgroundResource(R.drawable.play);
                    lottieAnimationView.pauseAnimation();
                    mp.pause();
                    count--;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mp.stop();
        finish();
    }

    public void loadSong(int index) {
        tvName.setText(names.get(index));
        ivSongImg.setImageResource(images.get(index));
        mp = MediaPlayer.create(PlayMusic.this, paths.get(index));
    }
}