package com.example.firebaseimage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firebaseimage.models.MobileModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class EditDataActivity extends AppCompatActivity {

    ImageView ivImage;
    DatabaseReference databaseReference;
    MobileModel mobile;
    Button btnUpdate;
    EditText edtName, edtPrice, edtRamRom, edtProcessor, edtCamera, edtDisplay, edtBattery, edtWarranty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Warning !!!");
        dialog.setMessage("You can not update Image. \nif you want to update image please delete record and again add it.");
        dialog.setPositiveButton("OK", null);
        dialog.setCancelable(false);
        dialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("myUploads");

        ivImage = findViewById(R.id.ivImage);
        btnUpdate = findViewById(R.id.btnUploadData);
        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtRamRom = findViewById(R.id.edtRamRom);
        edtProcessor = findViewById(R.id.edtProcessor);
        edtCamera = findViewById(R.id.edtCamera);
        edtDisplay = findViewById(R.id.edtDisplay);
        edtBattery = findViewById(R.id.edtBattery);
        edtWarranty = findViewById(R.id.edtWarranty);

        Intent i = getIntent();
        mobile = (MobileModel) i.getSerializableExtra("data");

        Picasso.with(this).load(mobile.getMobileImageURI()).into(ivImage);
        edtName.setText(mobile.getName());
        edtPrice.setText(mobile.getPrice());
        edtRamRom.setText(mobile.getRamRom());
        edtProcessor.setText(mobile.getProcessor());
        edtCamera.setText(mobile.getCamera());
        edtDisplay.setText(mobile.getDisplay());
        edtBattery.setText(mobile.getBattery());
        edtWarranty.setText(mobile.getWarranty());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtName.getText().toString().isEmpty() || edtProcessor.getText().toString().isEmpty() || edtBattery.getText().toString().isEmpty() || edtWarranty.getText().toString().isEmpty() || edtDisplay.getText().toString().isEmpty() || edtCamera.getText().toString().isEmpty() || edtRamRom.getText().toString().isEmpty()){
                    Toast.makeText(EditDataActivity.this, "All Fields are Required to Filled", Toast.LENGTH_SHORT).show();
                }
                else {
                    MobileModel mobileModel = new MobileModel(mobile.getFirebaseId(),mobile.getMobileImageURI(),edtName.getText().toString(),edtPrice.getText().toString(),edtRamRom.getText().toString(),edtProcessor.getText().toString(),edtCamera.getText().toString(),edtDisplay.getText().toString(),edtCamera.getText().toString(),edtWarranty.getText().toString());
                    updateData(mobileModel);
                    finish();
                }
            }
        });
    }

    private void updateData(MobileModel mobileModel){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        databaseReference.child(mobile.getFirebaseId()).setValue(mobileModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(EditDataActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                clearData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditDataActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearData(){
        edtName.setText("");
        edtPrice.setText("");
        edtRamRom.setText("");
        edtProcessor.setText("");
        edtDisplay.setText("");
        edtCamera.setText("");
        edtWarranty.setText("");
        edtDisplay.setText("");
        ivImage.setImageResource(R.drawable.add_xml);
    }
}