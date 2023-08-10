package com.example.firebaseimage.adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseimage.EditDataActivity;
import com.example.firebaseimage.R;
import com.example.firebaseimage.models.MobileModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    DatabaseReference databaseReference;
    StorageReference storageReference;
    Context context;
    ArrayList<MobileModel> mobileModels;
    public DataAdapter(Context context, ArrayList<MobileModel> mobileModels){
        this.context = context;
        this.mobileModels = mobileModels;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {

        Picasso.with(context).load(mobileModels.get(position).getMobileImageURI()).into(holder.ivImage);
        holder.tvName.setText(mobileModels.get(position).getName());
        holder.tvPrice.setText(String.format(Locale.US,mobileModels.get(position).getPrice()+" ₹"));
        holder.tvRamRom.setText(mobileModels.get(position).getRamRom());
        holder.tvBattery.setText(mobileModels.get(position).getBattery());
        holder.tvProcessor.setText(mobileModels.get(position).getProcessor());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog(holder.getAdapterPosition());
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iEdit = new Intent(context, EditDataActivity.class);
                iEdit.putExtra("data",mobileModels.get(holder.getAdapterPosition()));
                context.startActivity(iEdit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mobileModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvName, tvPrice, tvRamRom, tvProcessor, tvBattery;
        Button btnDelete, btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvProcessor = itemView.findViewById(R.id.tvProcessor);
            tvBattery = itemView.findViewById(R.id.tvBattery);
            tvRamRom = itemView.findViewById(R.id.tvRam);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    private void deleteMobileData(int position){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Deleting Record From Database...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(mobileModels.get(position).getMobileImageURI());
        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(context, "Mobile Deleted From Database", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("myUploads");
        databaseReference.child(mobileModels.get(position).getFirebaseId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showAlertDialog(int position){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Delete !");
        dialog.setMessage("Do You really want to delete Record ?");
        dialog.setCancelable(false);
        dialog.setIcon(R.drawable.delete);

        dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteMobileData(position);
            }
        });
        dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Data Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
