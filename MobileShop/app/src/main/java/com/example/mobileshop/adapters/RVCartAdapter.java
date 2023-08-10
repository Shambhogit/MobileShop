package com.example.mobileshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileshop.R;
import com.example.mobileshop.models.BuyModel;
import com.example.mobileshop.models.MobileModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVCartAdapter extends RecyclerView.Adapter<RVCartAdapter.ViewHolder> {

    Context context;
    ArrayList<MobileModel> mobileModels;

    public RVCartAdapter(Context context, ArrayList<MobileModel> mobileModels) {
        this.context = context;
        this.mobileModels = mobileModels;
    }

    @NonNull
    @Override
    public RVCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rvCard = LayoutInflater.from(context).inflate(R.layout.cart_place_order_card,parent,false);
        return new ViewHolder(rvCard);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(mobileModels.get(position).getMobileImageURI()).into(holder.ivImage);
        holder.tvName.setText(mobileModels.get(position).getName());
        holder.tvPrice.setText(mobileModels.get(position).getPrice());
        holder.tvRam.setText(mobileModels.get(position).getRamRom());
        holder.tvProcessor.setText(mobileModels.get(position).getProcessor());
        holder.tvCamera.setText(mobileModels.get(position).getCamera());
        holder.tvDisplay.setText(mobileModels.get(position).getDisplay());
        holder.tvBattery.setText(mobileModels.get(position).getBattery());
    }

    @Override
    public int getItemCount() {
        return mobileModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivImage;
        TextView tvName, tvPrice, tvRam, tvProcessor, tvCamera, tvDisplay, tvBattery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRam = itemView.findViewById(R.id.tvRam);
            tvProcessor = itemView.findViewById(R.id.tvProcessor);
            tvCamera = itemView.findViewById(R.id.tvCamera);
            tvDisplay = itemView.findViewById(R.id.tvDisplay);
            tvBattery = itemView.findViewById(R.id.tvBattery);
        }
    }
}
