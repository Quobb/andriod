package com.example.quobii;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
  EditText txtuser,txtuserpass;
  Button btnenter,btncan;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtuser = findViewById(R.id.txtname);
        txtuserpass = findViewById(R.id.txtpass);
        btnenter = findViewById(R.id.btnlog);
        btncan = findViewById(R.id.btncancel);
        btncan.setOnClickListener(v -> {
            txtuser.setText("");
            txtuserpass.setText("");
        });
        btnenter.setOnClickListener(v -> {
            if ( "Admin".equals(txtuser.getText().toString()) && "1234".equals(txtuserpass.getText().toString())){
                Intent kk = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(kk);
            }else{
                Toast.makeText(login.this,"invaid username or password" ,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void logback(View mm){
        Intent kk = new Intent(getApplicationContext(),mobile_computing.class);
        startActivity(kk);
    }

}