package com.example.login;

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

public class AdapterEx extends ArrayAdapter<Model> {

    ArrayList<Model> models;
    public AdapterEx(Context context, ArrayList<Model> models){
        super(context,0,models);
        this.models = models;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.grid_card,parent,false);
        }

        TextView tvType = v.findViewById(R.id.tvType);
        ImageView imgIcon = v.findViewById(R.id.ivIcon);

        tvType.setText(models.get(position).type);
        imgIcon.setImageResource(models.get(position).img);

        return v;
    }
}
