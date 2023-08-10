package com.example.mobileshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edtEmail, edtPassword;
    TextInputLayout tilEmail, tilPassword;
    ToastMessage toastMessage;
    FirebaseAuth auth;
    TextView tvForgotPassword, tvSignUp;
    Button btnLogin, btnGoogle, btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtLoginID);
        tilPassword = findViewById(R.id.tilPassword);
        tilEmail = findViewById(R.id.tilLoginID);
        auth = FirebaseAuth.getInstance();
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvSignUp = findViewById(R.id.tvSignUp);

        btnLogin = findViewById(R.id.btnLogin);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnFacebook = findViewById(R.id.btnFacebook);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSignup = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(iSignup);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateEmail() & validatePassword()){
                    loginUser(edtEmail.getText().toString(), edtPassword.getText().toString());
                    edtEmail.setText("");
                    edtPassword.setText("");
                }
            }
        });
    }

    private void loginUser(String mail, String password){
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Verifying Credentials...");
        progressDialog.show();

        auth.signInWithEmailAndPassword(mail, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Intent iHome = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(iHome);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(auth.getCurrentUser()!=null){
            Intent iHome = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(iHome);
        }
    }
    private boolean validateEmail(){
        if(edtEmail.getText().toString().isEmpty()){
            tilEmail.setError("Email is necessary");
            return false;
        }
        else{
            tilEmail.setError(null);
            return true;
        }
    }
    private boolean validatePassword(){
        if(edtPassword.getText().toString().isEmpty()){
            tilPassword.setError("Please Enter Password");

            return false;
        }
        else{
            tilPassword.setError(null);
            return true;
        }
    }

}