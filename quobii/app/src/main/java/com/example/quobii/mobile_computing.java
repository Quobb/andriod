package com.example.quobii;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class mobile_computing extends AppCompatActivity {
    Button clickme ;
    TextView textme;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_computing);
        clickme = findViewById(R.id.btnclickme);
        textme = findViewById(R.id.txthtuweb);

        clickme.setOnClickListener(v -> {
            Intent kk = new Intent(getApplicationContext(), login.class);
            startActivity(kk);
            finish();
        });
        textme.setOnClickListener(v -> {
            Intent ww = new Intent(Intent.ACTION_VIEW, Uri.parse("www.htu.edu.gh"));
            startActivity(ww);
            finish();
        });
    }
}