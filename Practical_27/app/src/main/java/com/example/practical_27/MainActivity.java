package com.example.practical_27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnSignUp;
    EditText edtLoginId, edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtLoginId = findViewById(R.id.edtLoginID);
        edtPassword = findViewById(R.id.edtPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUp);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginDatabase db = new LoginDatabase(MainActivity.this);
                String password = db.getIdPass(edtLoginId.getText().toString());

                if(password == null){
                    Toast.makeText(MainActivity.this, "Invalide data", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(edtPassword.getText().toString())){
                        Toast.makeText(MainActivity.this, "Valide user credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}