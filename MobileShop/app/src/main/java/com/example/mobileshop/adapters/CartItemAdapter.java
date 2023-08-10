package com.example.mobileshop.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobileshop.InfoPageActivity;
import com.example.mobileshop.models.MobileModel;
import com.example.mobileshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class CartItemAdapter extends ArrayAdapter<MobileModel> {
    Context context;
    ArrayList<MobileModel> mobileModels;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    public CartItemAdapter(@NonNull Context context, ArrayList<MobileModel> mobileModels) {
        super(context,0,mobileModels);
        this.context = context;
        this.mobileModels = mobileModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View card = convertView;
        if (card == null) {
            card = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }

        ImageView ivImage = card.findViewById(R.id.ivImage);
        TextView tvName = card.findViewById(R.id.tvName);
        TextView tvCost = card.findViewById(R.id.tvCost);
        TextView tvProcessor = card.findViewById(R.id.tvProcessor);
        TextView tvRam = card.findViewById(R.id.tvRam);
        Button btnDelete = card.findViewById(R.id.btnDelete);


        Picasso.with(context).load(mobileModels.get(position).getMobileImageURI()).into(ivImage);
        tvName.setText(mobileModels.get(position).getName());
        tvCost.setText(String.format(Locale.US,mobileModels.get(position).getPrice()+" ₹"));
        tvProcessor.setText(mobileModels.get(position).getProcessor());
        tvRam.setText(mobileModels.get(position).getRamRom());


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setTitle("Removing Item From Cart");
                progressDialog.show();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
                auth = FirebaseAuth.getInstance();
                databaseReference.child(auth.getCurrentUser().getUid()).child("userCart").child(mobileModels.get(position).getFirebaseId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InfoPageActivity.class);
                intent.putExtra("data",mobileModels.get(position));
                context.startActivity(intent);
            }
        });

        return card;
    }
}
