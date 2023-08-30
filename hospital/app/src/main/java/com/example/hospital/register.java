package com.example.hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText users, passs,conpass,email;
    Button logs ,signs;
    TextView signups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize UI elements
        users = findViewById(R.id.txtusername);
        passs = findViewById(R.id.txtpassname);
        email = findViewById(R.id.txtemail);
        conpass = findViewById(R.id.txtconpassname);
        logs = findViewById(R.id.btnlogs);
        signs = findViewById(R.id.btnsigns);
        signups = findViewById(R.id.signtxts);

        // Initially disable the "logs" button
        logs.setEnabled(false);

        // Handle "logs" button click
        logs.setOnClickListener(v -> {
            String username = users.getText().toString();
            String password = passs.getText().toString();
            String compass = conpass.getText().toString();
            String emails =   email.getText().toString();
            //database in section
             database dc = new database(getApplicationContext(),"hospital",null,1);


            if(username.length() == 0 || password.length()==0 || compass.length()==0 || emails.length()==0){
                // Show a toast if any field is empty
                Toast.makeText(getApplicationContext(),"Enter detail",Toast.LENGTH_SHORT).show();
            } else {
                if (password.compareTo(compass)==0){
                    if(isValid(password)){
                        dc.register(username,emails,password);
                        // Show success message and navigate to MainActivity
                        Toast.makeText(getApplicationContext(),"Account created successful",Toast.LENGTH_SHORT).show();
                        Intent dd = new Intent(getApplicationContext(), login.class);
                        startActivity(dd);
                        // Clear input fields
                        users.setText("");
                        passs.setText("");
                        conpass.setText("");
                        email.setText("");
                    } else {
                        // Show password complexity error message
                        Toast.makeText(getApplicationContext(),"Password must have at least a letter, a digit, and be 8 or more characters",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Show password mismatch error message
                    Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // TextWatcher to enable the "logs" button when passwords match
        TextWatcher jj = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = passs.getText().toString();
                String confirm = conpass.getText().toString();
                if(password.equals(confirm)){
                    logs.setEnabled(!confirm.isEmpty());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        passs.addTextChangedListener(jj);
        conpass.addTextChangedListener(jj);

        // Handle "signups" TextView click
        signups.setOnClickListener(v -> {
            // Navigate to login activity
            Intent mm = new Intent(getApplicationContext(), login.class);
            startActivity(mm);
        });

        // Handle "signs" button click
        signs.setOnClickListener(v -> {
            // Navigate to login activity
            Intent mm = new Intent(getApplicationContext(), login.class);
            startActivity(mm);
        });
    }

    // Method to check password complexity
    public static boolean isValid(String password){
        int f1=0,f2=0,f3=0;
        if(password.length() > 8){
            return false;
        } else {
            for(int p =0 ;p < password.length();p++){
                if (Character.isLetter(password.charAt(p))){
                    f1= 1;
                }
            }
            for(int s =0 ;s < password.length();s++){
                if (Character.isDigit(password.charAt(s))){
                    f2= 1;
                }
            }
            for(int c =0 ;c < password.length();c++){
                char r = password.charAt(c);
                if (r>=33&&r<=46||r==64){
                    f3= 1;
                }
            }
            if(f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }
}
