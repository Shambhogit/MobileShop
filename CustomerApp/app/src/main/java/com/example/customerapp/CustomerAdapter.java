package com.example.customerapp;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<CustomerModule> {
    Context context;
    ArrayList<CustomerModule> customerModules;

    public CustomerAdapter(@NonNull Context context, ArrayList<CustomerModule> customerModule) {
        super(context, 0);

        this.context = context;
        this.customerModules = customerModule;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View item = convertView;
        if(item == null){
            item = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        }

        TextView id = item.findViewById(R.id.tvId);
        TextView name = item.findViewById(R.id.tvName);
        TextView number = item.findViewById(R.id.tvNumber);
        TextView address = item.findViewById(R.id.tvAddress);
        TextView pin = item.findViewById(R.id.tvPin);

        id.setText(customerModules.get(position).id);
        name.setText(customerModules.get(position).name);
        number.setText(customerModules.get(position).number);
        address.setText(customerModules.get(position).address);
        pin.setText(customerModules.get(position).pinCode);

        return item;
    }
}
