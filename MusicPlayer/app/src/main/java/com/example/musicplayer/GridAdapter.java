package com.example.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<DataModule> {

    Context context;
    ArrayList<DataModule> dataModules;
    public GridAdapter(Context context, ArrayList<DataModule> dataModules){
        super(context,0,dataModules);

        this.context = context;
        this.dataModules = dataModules;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View card = convertView;
        if(card == null){
            card = LayoutInflater.from(context).inflate(R.layout.card_gv_type,parent,false);
        }

        TextView tvText = card.findViewById(R.id.tvText);
        ImageView ivImage = card.findViewById(R.id.ivImage);

        tvText.setText(dataModules.get(position).text);
        ivImage.setImageResource(dataModules.get(position).image);

        return card;
    }
}
