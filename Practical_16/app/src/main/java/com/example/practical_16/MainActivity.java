package com.example.practical_16;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Button btnTime,btnDate, btnOk;
    EditText edtTime,edtDate;
    DatePicker dpDate;
    TimePicker tpTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnOk = findViewById(R.id.btnOk);
        edtDate = findViewById(R.id.edtDate);
        edtTime = findViewById(R.id.edtTime);
        dpDate = findViewById(R.id.dpDate);
        tpTime = findViewById(R.id.tpTime);

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tpTime.setVisibility(View.VISIBLE);
                dpDate.setVisibility(View.GONE);

                String hour = Integer.toString(tpTime.getHour());
                String minute = Integer.toString(tpTime.getMinute());

                String time = hour+" : "+minute;
                edtTime.setText(time);
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tpTime.setVisibility(View.GONE);
                dpDate.setVisibility(View.VISIBLE);

                int month =  dpDate.getMonth();
                int day = dpDate.getDayOfMonth();
                int year = dpDate.getYear();

                String date =  day +"-"+month+"-"+year;

                edtDate.setText(date);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tpTime.setVisibility(View.GONE);
                dpDate.setVisibility(View.GONE);
            }
        });

    }
}