package com.example.signinpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtUserName, edtPassword;

    TextView tvSignUp, tvAnim, tvForgetPassword;
    Button btnLogin;

    int flag = 0;
    LoginDatabase db;
    
    ArrayList<UserModel> idPass = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignUp = findViewById(R.id.tvSingUp);
        tvAnim = findViewById(R.id.tvAnim);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.transpose);
        tvAnim.setAnimation(animation);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iSignUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(iSignUp);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new LoginDatabase(MainActivity.this);

                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();

                if(userName.isEmpty() || password.isEmpty()){
                    Toast toast = new Toast(MainActivity.this);
                    View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.worning_toast,(ViewGroup) findViewById(R.id.ll_toast_warning_fields));
                    toast.setView(v);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    idPass = db.checkUserNamePassword(userName,password);
                    if(idPass.size() == 0){

                        Toast toast = new Toast(MainActivity.this);
                        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.error_user,(ViewGroup) findViewById(R.id.ll_toast_user_wrong));
                        toast.setView(v);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                        clear();
                    }
                    else{
                        if(idPass.get(0).password.equals(password)){
                            Toast succ = new Toast(MainActivity.this);
                            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.login_success_toast,(ViewGroup) findViewById(R.id.ll_toast_success));
                            succ.setView(v);
                            succ.setDuration(Toast.LENGTH_LONG);
                            succ.show();
                            flag = 1;
                            Intent iHome = new Intent(MainActivity.this, HomePage.class);
                            startActivity(iHome);
                            finish();

                            clear();
                        }
                        else{
                            Toast succ = new Toast(MainActivity.this);
                            View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.pass_error_toast,(ViewGroup) findViewById(R.id.ll_toast_pass_wrong));
                            succ.setView(v);
                            succ.setDuration(Toast.LENGTH_LONG);
                            succ.show();
                            clear();
                        }
                    }

                }
//                Toast.makeText(MainActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iForget = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(iForget);
            }
        });

        Toast.makeText(this, "Oncreate()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();

        if(flag == 1){
            Intent iHome = new Intent(MainActivity.this, HomePage.class);
            startActivity(iHome);
        }
    }

    public void clear(){
        edtPassword.setText("");
        edtUserName.setText("");
    }
}