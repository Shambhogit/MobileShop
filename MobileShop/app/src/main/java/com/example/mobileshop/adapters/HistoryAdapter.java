package com.example.mobileshop.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileshop.models.BuyModel;
import com.example.mobileshop.R;
import com.example.mobileshop.models.CartOrderHistory;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    Context context;
    ArrayList<CartOrderHistory> orderHistories;

    public HistoryAdapter(@NonNull Context context,ArrayList<CartOrderHistory> orderHistories) {
        this.context = context;
        this.orderHistories = orderHistories;
    }


    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(context).inflate(R.layout.history_cart,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        if(position == 0) {
            if(orderHistories.get(position).getBuyModels().size() == 0){
                holder.tvName.setVisibility(View.GONE);
            }
            else {
                holder.tvName.setText("Orders History");
                holder.recyclerView.setAdapter(new OrderHistoryAdapter(context,orderHistories.get(position).getBuyModels()));
            }


        }
        else{
            holder.tvName.setText(String.format(Locale.US,"Cart Order No "+position));
            holder.recyclerView.setAdapter(new OrderHistoryAdapter(context,orderHistories.get(position).getBuyModels()));
            holder.recyclerView.smoothScrollToPosition(orderHistories.get(position).getBuyModels().size()-1);
        }

    }

    @Override
    public int getItemCount() {
        return orderHistories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            recyclerView = itemView.findViewById(R.id.rvHistory);
        }
    }
}
