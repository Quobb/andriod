package com.example.quobii;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    EditText txtuser, txtuserpass, txtconfirm, txtemail, txtname;
    Button btnenter, btncan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtuser = findViewById(R.id.txtusername);
        txtconfirm = findViewById(R.id.txtconfirmPass);
        txtuserpass = findViewById(R.id.txtpassword);
        txtemail = findViewById(R.id.txtemail);
        txtname = findViewById(R.id.txtfullname);
        btnenter = findViewById(R.id.btnsign);
        btncan = findViewById(R.id.btncan);

    }
}