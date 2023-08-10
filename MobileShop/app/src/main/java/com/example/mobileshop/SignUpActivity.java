package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileshop.models.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout tilName, tilPhone, tilAddress, tilEmail, tilPassword;
    TextInputEditText edtName, edtPhone, edtAddress, edtEmail, edtPassword;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    TextView tvLogin;
    Button btnSignup, btnFacebook, btnGoogle;
    String name, phone, address, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        tilName = findViewById(R.id.tilName);
        tilPhone = findViewById(R.id.tilPhone);
        tilAddress = findViewById(R.id.tilAddress);
        tilEmail = findViewById(R.id.tilLoginID);
        tilPassword = findViewById(R.id.tilPassword);

        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtLoginID);
        edtPassword = findViewById(R.id.edtPassword);

        tvLogin = findViewById(R.id.tvLogin);

        btnSignup = findViewById(R.id.btnSignup);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iLogin = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(iLogin);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                tilPassword.setError(null);
                return false;
            }
        });
    }

    private void createAccount(){
        if(validateName() & validatePhone() & validateAddress() & validateEmail() & validatePassword()) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Creating Account...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            String imageUriDefault ="https://firebasestorage.googleapis.com/v0/b/fir-image-12ac2.appspot.com/o/userProfilePhoto%2Fuser.png?alt=media&token=e635dbb8-49c8-4bd6-83e7-ae5170ff9bda";
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }

                    databaseReference.child(task.getResult().getUser().getUid()).child("info").setValue(new UserInfo(name, phone, address, email, imageUriDefault)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                        }
                    });
                    clearAllFields();

                    Intent iHome = new Intent(SignUpActivity.this, MainActivity.class);
                    if(auth.getCurrentUser() != null){
                        startActivity(iHome);
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean validateName(){
        if(edtName.getText().toString().isEmpty()){
            tilName.setError("Please Enter Name");
            return false;
        }
        else{
            tilName.setError(null);
            name = edtName.getText().toString();
            return true;
        }
    }
    private boolean validatePhone(){
        if(edtPhone.getText().toString().isEmpty()){
            tilPhone.setError("Enter phone number");
            return false;
        }
        else{
            tilPhone.setError(null);
            phone = edtPhone.getText().toString();
            return true;
        }
    }
    private boolean validateAddress(){
        if(edtAddress.getText().toString().isEmpty()){
            tilAddress.setError("Address is necessary");
            return false;
        }
        else{
            tilAddress.setError(null);
            address = edtAddress.getText().toString();
            return true;
        }
    }
    private boolean validateEmail(){
        if(edtEmail.getText().toString().isEmpty()){
            tilEmail.setError("Email is necessary");
            return false;
        }
        else{
            tilEmail.setError(null);
            email = edtEmail.getText().toString();
            return true;
        }
    }
    private boolean validatePassword(){
        if(edtPassword.getText().toString().isEmpty()){
            tilPassword.setError("Please Enter Password");

            return false;
        }
        else{
            if(edtPassword.getText().toString().length() < 8){
                tilPassword.setError("At least 8 characters");
                return false;
            }
            password = edtPassword.getText().toString();
            tilPassword.setError(null);
            return true;
        }
    }
    private void clearAllFields(){
        edtEmail.setText("");
        edtName.setText("");
        edtPhone.setText("");
        edtAddress.setText("");
        edtPassword.setText("");
    }
}