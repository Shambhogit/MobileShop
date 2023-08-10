package com.example.signinpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText edtUserName, edtEmailId, edtPassword, edtHobby;
    Button btnCreate;

    String userName, email, password, hobby;

    TextView tvAnim;

    LoginDatabase db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtUserName = findViewById(R.id.edtUserName);
        edtHobby = findViewById(R.id.edtHobby);
        edtEmailId = findViewById(R.id.edtEmailId);
        edtPassword = findViewById(R.id.edtPassword);
        tvAnim = findViewById(R.id.tvAnim);

        Animation animation = AnimationUtils.loadAnimation(SignUp.this,R.anim.transpose);
        tvAnim.setAnimation(animation);

        btnCreate = findViewById(R.id.btnCreate);
        db = new LoginDatabase(SignUp.this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = edtUserName.getText().toString();
                hobby = edtHobby.getText().toString();
                email = edtEmailId.getText().toString();
                password = edtPassword.getText().toString();




                if(userName.isEmpty() || hobby.isEmpty() || password.isEmpty() || email.isEmpty()){
                    Toast toast = new Toast(SignUp.this);
                    View v = LayoutInflater.from(SignUp.this).inflate(R.layout.worning_toast,(ViewGroup) findViewById(R.id.ll_toast_warning_fields));
                    toast.setView(v);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(password.length()<8){
                    Toast toast = new Toast(SignUp.this);
                    View v = LayoutInflater.from(SignUp.this).inflate(R.layout.error_size,(ViewGroup) findViewById(R.id.ll_toast_pass_size));
                    toast.setView(v);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    db.insertUserData(email,userName,password,hobby);
                    db.insertData(userName,password);
                    Toast toast = new Toast(SignUp.this);
                    View v = LayoutInflater.from(SignUp.this).inflate(R.layout.account_created_toast,(ViewGroup) findViewById(R.id.ll_toast_created));
                    toast.setView(v);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                }
            }
        });
    }
}