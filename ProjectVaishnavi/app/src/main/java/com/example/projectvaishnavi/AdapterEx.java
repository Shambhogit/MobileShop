package com.example.projectvaishnavi;

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
    public AdapterEx(Context context, ArrayList<Model> phoneModelArrayList){
        super(context,0,phoneModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.mobile_card ,parent,false);
        }

        Model phoneModel = getItem(position);
        TextView tvName = v.findViewById(R.id.name);
        TextView tvPrice = v.findViewById(R.id.price);
        TextView tvOff = v.findViewById(R.id.off);
        ImageView ivImage = v.findViewById(R.id.ivImage);

        tvName.setText(phoneModel.name);
        tvPrice.setText(phoneModel.price);
        tvOff.setText(phoneModel.off);
        ivImage.setImageResource(phoneModel.img);
        return v;
    }
}