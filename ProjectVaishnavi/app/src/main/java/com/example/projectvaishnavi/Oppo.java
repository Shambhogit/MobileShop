package com.example.projectvaishnavi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class Oppo extends AppCompatActivity {

    GridView gvOppo ;
    ArrayList<Model> phones = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oppo);

        gvOppo = findViewById(R.id.gvOppo);
        phones.add(new Model(R.drawable.oppoa17,"OPPO A17","₹12,499","29%"));
        phones.add(new Model(R.drawable.oppoa17k,"OPPO A17K","₹14,499","15%"));
        phones.add(new Model(R.drawable.oppoa57,"OPPO A57","₹11,999","19%"));
        phones.add(new Model(R.drawable.oppoa77s,"OPPO A77s","₹22,399","15%"));
        phones.add(new Model(R.drawable.oppof21,"OPPO F21","₹32,499","29%"));
        phones.add(new Model(R.drawable.oppof23,"OPPO F23","₹29,599","16%"));
        phones.add(new Model(R.drawable.ok3,"OPPO K3","₹9,599","13%"));

        AdapterEx adapterEx = new AdapterEx(Oppo.this,phones);
        gvOppo.setAdapter(adapterEx);

    }
}