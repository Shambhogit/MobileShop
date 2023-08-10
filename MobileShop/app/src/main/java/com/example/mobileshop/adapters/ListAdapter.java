package com.example.mobileshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileshop.models.InfoModel;
import com.example.mobileshop.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<InfoModel> {
    Context context;
    ArrayList<InfoModel> infoModels ;
    public ListAdapter(@NonNull Context context, ArrayList<InfoModel> infoModels) {
        super(context, 0, infoModels);

        this.context = context;
        this.infoModels = infoModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        }

        TextView tvName = listItem.findViewById(R.id.tvName);
        TextView tvInfo = listItem.findViewById(R.id.tvInfo);

        tvName.setText(infoModels.get(position).name);
        tvInfo.setText(infoModels.get(position).info);

        return listItem;
    }
}
