package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText user, pass;
    Button log ,sign;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        user = findViewById(R.id.txtusername);
        pass = findViewById(R.id.txtpassname);
        log = findViewById(R.id.btnlogs);
        sign = findViewById(R.id.btnsigns);
        signup = findViewById(R.id.signtxts);

        // Initially disable the "log" button
        log.setEnabled(false);

        // Handle "log" button click
        log.setOnClickListener(v -> {
            String username = user.getText().toString();
            String password = pass.getText().toString();
            database dc = new database(getApplicationContext(),"hospital",null,1);
            if(username.length() == 0 || password.length()==0){
                // Show a toast if any field is empty
                Toast.makeText(getApplicationContext(),"Enter details",Toast.LENGTH_SHORT).show();
            } else {
                // Navigate to MainActivity and show login success message
                if(dc.login(username,password)==1){

                    Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT).show();

                    SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.putString("username",username);
                    ed.apply();

                    Intent dd = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(dd);
                    // Clear input fields
                    clear();
                }else{
                    Toast.makeText(getApplicationContext(),"invaild user name and password",Toast.LENGTH_SHORT).show();
                }
                // Clear input fields
                clear();
            }
        });

        // Handle "signup" TextView click
        signup.setOnClickListener(v -> {
            // Navigate to registration activity
            Intent hh = new Intent(getApplicationContext(), register.class);
            startActivity(hh);
        });

        // Handle "sign" button click (likely a duplicate, can be removed)
        sign.setOnClickListener(v -> {
            // Navigate to registration activity
            Intent hh = new Intent(getApplicationContext(), register.class);
            startActivity(hh);
        });

        // TextWatcher to enable the "log" button when both fields are not empty
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
    public void clear(){
        user.setText("");
        pass.setText("");
    }
}
