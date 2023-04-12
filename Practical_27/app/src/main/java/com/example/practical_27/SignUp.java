package com.example.practical_27;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    Button btnSubmit;
    EditText edtName, edtPassword, edtPhone, edtUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSubmit = findViewById(R.id.btnSubmit);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPass);
        edtPhone = findViewById(R.id.edtPhone);
        edtUserId = findViewById(R.id.edtID);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String userId = edtUserId.getText().toString();
                String password = edtPassword.getText().toString();

                Toast.makeText(SignUp.this, edtName.getText().toString(), Toast.LENGTH_SHORT).show();

                if(name.isEmpty() || phone.isEmpty() || userId.isEmpty() || password.isEmpty()||name.equals("")||phone.equals("")||userId.equals("")||password.equals("")){
                    AlertDialog.Builder error = new AlertDialog.Builder(SignUp.this);
                    error.setTitle("Error!!!");
                    error.setMessage("All fields need to be filled");
                    error.setIcon(R.drawable.error);
                    error.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(SignUp.this, "Enter fields which are empty", Toast.LENGTH_SHORT).show();
                        }
                    });
                    error.create();
                    error.show();
                }
                else{
                    LoginDatabase db = new LoginDatabase(SignUp.this);
                    db.addLoginId(name,phone,userId,password);
                    Toast.makeText(SignUp.this, "Data is inserted Successfully\n\t\tyou can login now", Toast.LENGTH_SHORT).show();
                    SignUp.this.finish();
                }
            }
        });
    }
}