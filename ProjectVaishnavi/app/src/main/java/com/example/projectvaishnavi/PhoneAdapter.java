package com.example.projectvaishnavi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class PhoneAdapter extends ArrayAdapter<PhoneModel> {
    public PhoneAdapter(Context context, ArrayList<PhoneModel> phoneModelArrayList){
        super(context,0,phoneModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.grid_card,parent,false);
        }

        PhoneModel phoneModel = getItem(position);
        TextView tvName = v.findViewById(R.id.name);
        ImageView ivImage = v.findViewById(R.id.ivImage);

        tvName.setText(phoneModel.name);
        ivImage.setImageResource(phoneModel.imgid);
        return v;
    }
}
