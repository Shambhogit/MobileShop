package com.example.projectvaishnavi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PhoneModel> phoneArray = new ArrayList<>();
    GridView gvCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvCard = findViewById(R.id.gvComp);

        phoneArray.add(new PhoneModel("OPPO",R.drawable.oppo));
        phoneArray.add(new PhoneModel("APPLE",R.drawable.apple));
        phoneArray.add(new PhoneModel("GOOGLE",R.drawable.google));
        phoneArray.add(new PhoneModel("XIAOMI",R.drawable.xiaomi));
        phoneArray.add(new PhoneModel("ONE PLUS",R.drawable.oneplus));
        phoneArray.add(new PhoneModel("SAMSUNG",R.drawable.samsung));
        phoneArray.add(new PhoneModel("MOTOROLA",R.drawable.motorola));
        phoneArray.add(new PhoneModel("SONY",R.drawable.sony));

        PhoneAdapter adapter = new PhoneAdapter(MainActivity.this,phoneArray);

        gvCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0 :
                        Intent iOppo = new Intent(MainActivity.this,Oppo.class);
                        startActivity(iOppo);
                        Toast.makeText(MainActivity.this, ""+0, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, ""+1, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivity.this, ""+2, Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this, ""+3, Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(MainActivity.this, ""+4, Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(MainActivity.this, ""+5, Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(MainActivity.this, ""+6, Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(MainActivity.this, ""+7, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Click On Right option", Toast.LENGTH_SHORT).show();
                }
            }
        });
        gvCard.setAdapter(adapter);
    }
}