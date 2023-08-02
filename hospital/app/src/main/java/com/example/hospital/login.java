package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText user, pass;
    Button log ,sign;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.txtuser);
        pass = findViewById(R.id.txtpass);
        log = findViewById(R.id.btnlog);
        sign = findViewById(R.id.btnsign);
        log.setOnClickListener(v -> {
            String username = user.getText().toString();
            String password = pass.getText().toString();
            if(username.length() == 0 || password.length()==0){
                Toast.makeText(getApplicationContext(),"Enter detail",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"log successful",Toast.LENGTH_LONG).show();
            }

            Toast.makeText(getApplicationContext(),"log successful",Toast.LENGTH_LONG).show();
            Intent dd = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(dd);
        });
        TextWatcher kk = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                log.setEnabled(!(username.isEmpty() || password.isEmpty()));

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        user.addTextChangedListener(kk);
        pass.addTextChangedListener(kk);
    }
  public void regis(){
        Intent hh = new Intent(getApplicationContext(), register.class);
        startActivity(hh);
  }
}