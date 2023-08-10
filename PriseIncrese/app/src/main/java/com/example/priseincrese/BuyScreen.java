package com.example.priseincrese;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BuyScreen extends AppCompatActivity {

    ImageView imgPhone;
    TextView tvName, tvPrice, tvTotalPrice;
    EditText edtQuantity;
    Button btnBuy;

    int price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_screen);

        imgPhone = findViewById(R.id.imgPhone);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnBuy = findViewById(R.id.btnBuy);

        Intent iGet = getIntent();

        price = Integer.parseInt(iGet.getStringExtra("price"));
        tvName.setText(iGet.getStringExtra("name"));
        tvPrice.setText(iGet.getStringExtra("price"));
        Bundle data = iGet.getExtras();

        imgPhone.setImageResource(data.getInt("image"));

        edtQuantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if(edtQuantity.getText().toString().isEmpty()||edtQuantity.getText().toString().equals("")){
                    tvTotalPrice.setText("0");
                    Toast.makeText(BuyScreen.this, "No Quantity", Toast.LENGTH_SHORT).show();
                }
                else{
                    int quantity = Integer.parseInt(edtQuantity.getText().toString());
                    int total = quantity * price;
                    tvTotalPrice.setText(""+total);
                }
                return false;
            }
        });
//        Toast.makeText(this, ""+iGet.getIntExtra("price",0)+" "+iGet.getStringExtra("name"), Toast.LENGTH_SHORT).show();
    }
}