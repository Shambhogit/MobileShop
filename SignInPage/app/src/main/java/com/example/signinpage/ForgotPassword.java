package com.example.signinpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    TextView tvAnim;
    EditText edtUserName, edtHobby;
    Button btnShowPass;
    String userName, hobby;

    LoginDatabase db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tvAnim = findViewById(R.id.tvAnim);
        edtUserName = findViewById(R.id.edtUserName);
        edtHobby = findViewById(R.id.edtHobby);
        btnShowPass = findViewById(R.id.btnShowPass);
        db = new LoginDatabase(ForgotPassword.this);

        Animation anim = AnimationUtils.loadAnimation(ForgotPassword.this,R.anim.transpose);
        tvAnim.setAnimation(anim);

        btnShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = edtUserName.getText().toString();
                hobby = edtHobby.getText().toString();

                if(userName.isEmpty() || hobby.isEmpty()){
                    Toast toast = new Toast(ForgotPassword.this);
                    View v = LayoutInflater.from(ForgotPassword.this).inflate(R.layout.worning_toast,(ViewGroup) findViewById(R.id.ll_toast_warning_fields));
                    toast.setView(v);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    String dataHobby = db.getHobby(userName).toString();
                    if(dataHobby.isEmpty()){
                        Toast toast = new Toast(ForgotPassword.this);
                        View v = LayoutInflater.from(ForgotPassword.this).inflate(R.layout.error_user,(ViewGroup) findViewById(R.id.ll_toast_user_wrong));
                        toast.setView(v);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.show();
                        clear();
                    }
                    else{
                        if(dataHobby.equals(hobby)){
                            String password = db.getPassword(userName).toString();
                            Toast pass = new Toast(ForgotPassword.this);
                            View v = LayoutInflater.from(ForgotPassword.this).inflate(R.layout.shwo_password_toast,(ViewGroup) findViewById(R.id.ll_toast_info));
                            TextView passwordTv = v.findViewById(R.id.tvPass);
                            passwordTv.setText(password);
                            pass.setView(v);
                            pass.setDuration(Toast.LENGTH_LONG);
                            pass.setGravity(Gravity.CENTER,0,0);
                            pass.show();
                            clear();
                            finish();
                        }
                        else{
                            Toast toast = new Toast(ForgotPassword.this);
                            View v = LayoutInflater.from(ForgotPassword.this).inflate(R.layout.warning_hobby,(ViewGroup) findViewById(R.id.ll_toast_warning_hobby));
                            toast.setView(v);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                            clear();
                        }
                    }
                }
            }
        });
    }

    public void clear(){
        edtHobby.setText("");
        edtUserName.setText("");
    }
}