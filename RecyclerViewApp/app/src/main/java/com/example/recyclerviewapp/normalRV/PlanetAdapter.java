package com.example.recyclerviewapp.normalRV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewapp.R;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Locale;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetHolder> {

    //Adapter class has 2 classes
    //1- Planet Adapter
    //2- Planet Holder

    private Context context;
    private ArrayList<Planet> planetArrayList;

    public PlanetAdapter(Context context, ArrayList<Planet> planetArrayList) {
        this.context = context;
        this.planetArrayList = planetArrayList;
    }


    @NonNull
    @Override
    public PlanetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.planet_layout_item,parent,false);
        return new PlanetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetAdapter.PlanetHolder holder, int position) {
        Planet planet = planetArrayList.get(position);
        holder.setDetails(planet);
    }

    @Override
    public int getItemCount() {
        return planetArrayList.size();
    }

    class PlanetHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvDistance, tvGravity, tvDiameter;


        public PlanetHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvPlanetName);
            tvDistance = itemView.findViewById(R.id.tvDistanceFromSun);
            tvDiameter = itemView.findViewById(R.id.tvDimeter);
            tvGravity = itemView.findViewById(R.id.tvGravity);
        }

        void setDetails(Planet planet){
            tvName.setText(planet.getPlanetName());
            tvDistance.setText(String.format(Locale.US,"Distance from sun : %d million KM",planet.getDistanceFromSun()));
            tvGravity.setText(String.format(Locale.US,"Surface Gravity : %d N/Kg",planet.getGravity()));
            tvDiameter.setText(String.format(Locale.US,"Diameter : %d KM",planet.getDiameter()));
        }
    }
}
