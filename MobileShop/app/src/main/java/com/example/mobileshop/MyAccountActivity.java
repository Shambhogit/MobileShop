package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobileshop.models.UserInfo;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class MyAccountActivity extends AppCompatActivity {

    ShapeableImageView ivUploadImage;
    UserInfo userInfo;
    Uri uri;
    TextInputLayout tilName, tilAddress;
    Bitmap scaledImage;
    TextInputEditText edtName, edtAddress;
    Button btnSubmit, btnGallery, btnCamera, btnCancel;
    private static final int CAMERA_REQ_CODE = 101;
    private static final int GALLERY_REQ_CODE = 102;
    StorageReference storageReference;
    Dialog dialog;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        getSupportActionBar().hide();
        ActivityCompat.requestPermissions(MyAccountActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},100);


        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(auth.getCurrentUser().getUid()).child("info");

        ivUploadImage = findViewById(R.id.ivUploadImage);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
        tilAddress = findViewById(R.id.tilAddress);
        tilName = findViewById(R.id.tilName);

        setDataToFields();
        validateUserInput();
        ivUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChooser();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadDataToDatabase();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQ_CODE){
            if(data != null){
                try{
                    Bitmap img = (Bitmap) data.getExtras().get("data");
                    scaledImage = Bitmap.createScaledBitmap(img,150,150,false);
                    ivUploadImage.setImageBitmap(scaledImage);
                    storeProfileImage();
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }
                }catch (Exception e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(requestCode == GALLERY_REQ_CODE){
            if(data != null){
                try{
                    uri = data.getData();
                    Bitmap img = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    scaledImage = Bitmap.createScaledBitmap(img,150,150,false);
                    ivUploadImage.setImageBitmap(scaledImage);
                    storeProfileImage();
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }
                }catch (Exception e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void storeProfileImage(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Image is Uploading");
        progressDialog.show();
        StorageReference imageFile =  FirebaseStorage.getInstance().getReference("userProfilePhoto").child(auth.getCurrentUser().getUid()+".jpeg");
        UploadTask uploadTask = imageFile.putFile(getImageUri(this,scaledImage));

        Task<Uri> downloadUri = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()){
                    Toast.makeText(MyAccountActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                return imageFile.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(progressDialog.isShowing()) progressDialog.dismiss();
                databaseReference.child("profileImageUrl").setValue(task.getResult().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MyAccountActivity.this, "Image Loadead", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void setDataToFields(){
        String defaultImage = "https://firebasestorage.googleapis.com/v0/b/fir-image-12ac2.appspot.com/o/userProfilePhoto%2Fuser.png?alt=media&token=e635dbb8-49c8-4bd6-83e7-ae5170ff9bda";
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userInfo = snapshot.getValue(UserInfo.class);
                edtName.setText(userInfo.getName());
                edtAddress.setText(userInfo.getAddress());
                if(defaultImage.equals(userInfo.getProfileImageUrl())){
                    ivUploadImage.setImageResource(R.drawable.add_user);
                }
                else{
                    Picasso.with(MyAccountActivity.this).load(userInfo.getProfileImageUrl()).into(ivUploadImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyAccountActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Uri getImageUri(Context context, Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(),bitmap,auth.getCurrentUser().getUid(),null);
        return Uri.parse(path);
    }

    private void showChooser(){
        dialog = new Dialog(MyAccountActivity.this);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(R.layout.select_image_dialog);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        btnCamera = dialog.findViewById(R.id.btnCamera);
        btnGallery = dialog.findViewById(R.id.btnGallery);
        btnCancel = dialog.findViewById(R.id.btnCancle);

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_GET_CONTENT);
                iGallery.setType("image/*");
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera,CAMERA_REQ_CODE);
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialog.isShowing()){
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void validateUserInput(){
        edtName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(edtName.getText().toString().isEmpty()){
                    tilName.setError("This field can't be empty");
                }
                else{
                    tilName.setError(null);
                }
                return false;
            }
        });
        edtAddress.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(edtAddress.getText().toString().isEmpty()){
                    tilAddress.setError("This field can't be empty");
                }
                else{
                    tilAddress.setError(null);
                }
                return false;
            }
        });
    }
    private void loadDataToDatabase(){
        if(edtName.getText().toString().isEmpty()){
            tilName.setError("This field can't be empty");
        } else if (edtAddress.getText().toString().isEmpty()) {
            tilAddress.setError("This field can't be empty");
        }
        else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Data is Uploading");
            progressDialog.show();
            UserInfo newUser = new UserInfo(edtName.getText().toString(),userInfo.getPhone(), edtAddress.getText().toString(), userInfo.getEmail(), userInfo.getProfileImageUrl());
            databaseReference.setValue(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Toast.makeText(MyAccountActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}