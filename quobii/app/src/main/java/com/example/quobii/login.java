package com.example.quobii;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    EditText txtuser, txtuserpass;
    Button btnenter, btncan;
    View newmwin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtuser = findViewById(R.id.txtname);
        txtuserpass = findViewById(R.id.txtpass);
        btnenter = findViewById(R.id.btnlog);
        btncan = findViewById(R.id.btncancel);
        newmwin = findViewById(R.id.main);
        btncan.setOnClickListener(v -> {
            txtuser.setText("");
            txtuserpass.setText("");
        });
        btnenter.setOnClickListener(v -> {
            if ("Admin".equals(txtuser.getText().toString()) && "1234".equals(txtuserpass.getText().toString())) {
                Intent kk = new Intent(getApplicationContext(), menu.class);
                String hello = txtuser.getText().toString();
                kk.putExtra("user", hello);
                kk.putExtra("pass", txtuserpass.getText().toString());
                txtuser.setText("");
                txtuserpass.setText("");
                startActivity(kk);
            } else {
                Toast.makeText(login.this, "invaid username or password", Toast.LENGTH_SHORT).show();
            }
        });
        btnenter.setEnabled(false);

        // Set up a common text change listener for both EditText fields
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Enable the login button if both username and password are not empty
                boolean isUsernameEmpty = txtuser.getText().toString().trim().isEmpty();
                boolean isPasswordEmpty = txtuserpass.getText().toString().trim().isEmpty();
                btnenter.setEnabled(!(isUsernameEmpty || isPasswordEmpty));


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        txtuser.addTextChangedListener(textWatcher);
        txtuserpass.addTextChangedListener(textWatcher);


    }

    public void logback(View mm) {
        Intent kk = new Intent(getApplicationContext(), mobile_computing.class);
        startActivity(kk);
    }

}