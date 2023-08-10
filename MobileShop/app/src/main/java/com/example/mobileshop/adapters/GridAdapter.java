package com.example.mobileshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileshop.InfoPageActivity;
import com.example.mobileshop.models.MobileModel;
import com.example.mobileshop.R;

import java.util.ArrayList;
import java.util.Locale;
import com.squareup.picasso.Picasso;

public class GridAdapter extends ArrayAdapter<MobileModel> {

    Context context;

    ArrayList<MobileModel> mobileModels;
    public GridAdapter(@NonNull Context context, ArrayList<MobileModel> mobileModels) {
        super(context,0,mobileModels);
        this.context = context;
        this.mobileModels = mobileModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View card = convertView;
        if(card == null){
            card = LayoutInflater.from(context).inflate(R.layout.mobile_card,parent,false);
        }

        ImageView img = card.findViewById(R.id.ivMobileImage);
        TextView name = card.findViewById(R.id.tvName);
        TextView price = card.findViewById(R.id.tvPrice);



        Picasso.with(context).load(mobileModels.get(position).getMobileImageURI()).into(img);
        name.setText(mobileModels.get(position).getName());
        price.setText(String.format(Locale.US,mobileModels.get(position).getPrice()+" ₹"));

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,InfoPageActivity.class);
                intent.putExtra("data",mobileModels.get(position));
                context.startActivity(intent);
            }
        });

        return card;
    }
}
