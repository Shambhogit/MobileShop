package com.example.mobileshop.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileshop.models.BuyModel;
import com.example.mobileshop.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<BuyModel> buyModels;

    public OrderHistoryAdapter(@NonNull Context context, ArrayList<BuyModel> buyModels) {
        this.context = context;
        this.buyModels = buyModels;
    }


    @NonNull
    @Override
    public OrderHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(context).inflate(R.layout.order_history_item,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(buyModels.get(position).getImage()).into(holder.ivImage);
        holder.tvName.setText(buyModels.get(position).getName());
        holder.tvPrice.setText(buyModels.get(position).getPrice());
        holder.tvQuantity.setText(String.format(Locale.US,"Quantity : "+buyModels.get(position).getQuantity()));
        holder.tvPaymentOption.setText(buyModels.get(position).getPaymentOption());
        holder.tvCustomerName.setText(buyModels.get(position).getCustomer_name());
        holder.tvMobileNo.setText(buyModels.get(position).getMobile_no());
        holder.tvTotal.setText(buyModels.get(position).getTotal_price());

    }

    @Override
    public int getItemCount() {
        return buyModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImage;
        TextView tvName, tvPrice, tvQuantity, tvPaymentOption, tvCustomerName, tvMobileNo, tvTotal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPaymentOption = itemView.findViewById(R.id.tvPaymentOption);
            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvMobileNo = itemView.findViewById(R.id.tvMobileNo);
            tvTotal = itemView.findViewById(R.id.tvTotal);
        }
    }
}
