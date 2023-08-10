package com.example.recyclerviewapp.normalRV;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerviewapp.R;

import java.util.ArrayList;

public class NormalRV extends AppCompatActivity {

    //this is data for recycler view and planet is model class of data
    private ArrayList<Planet> planetArrayList = new ArrayList<>();
    //this is adapter class for recycler view
    private PlanetAdapter adapter;
    RecyclerView normalRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_rv);
        normalRV = findViewById(R.id.rvNormal);

        setData();
        //set Layout manager to the recycler view
        normalRV.setLayoutManager(new LinearLayoutManager(this));

        //creating object of adapter and passing data to is
        adapter = new PlanetAdapter(this,planetArrayList);

        //setting the adapter to recycler view
        normalRV.setAdapter(adapter);

        //setting decoration to RV so that it splits in between
        normalRV.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
    }

    private void setData(){
        planetArrayList.add(new Planet("Earth",150,10,12750));
        planetArrayList.add(new Planet("Mars",227,10,6792));
        planetArrayList.add(new Planet("Jupiter",778,10,142984));
        planetArrayList.add(new Planet("Venus",108,10,12104));
        planetArrayList.add(new Planet("Saturn",1433,10,120356));
        planetArrayList.add(new Planet("Neptune",4495,10,49528));
        planetArrayList.add(new Planet("Uranus",2872,10,2872));
        planetArrayList.add(new Planet("Earth",150,10,12750));
        planetArrayList.add(new Planet("Mars",227,10,6792));
        planetArrayList.add(new Planet("Jupiter",778,10,142984));
        planetArrayList.add(new Planet("Venus",108,10,12104));
        planetArrayList.add(new Planet("Saturn",1433,10,120356));
        planetArrayList.add(new Planet("Neptune",4495,10,49528));
        planetArrayList.add(new Planet("Uranus",2872,10,2872));
    }
}