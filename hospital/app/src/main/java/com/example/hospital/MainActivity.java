package com.example.hospital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    CardView lab, med, find, health, order, exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lab = findViewById(R.id.cardLab);
        med = findViewById(R.id.cardMedicine);
        find = findViewById(R.id.cardFindDoctor);
        health = findViewById(R.id.cardHealth);
        order = findViewById(R.id.cardOrder);
        exit = findViewById(R.id.cardExit);


        SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sp.getString("username", "");
        Toast.makeText(getApplicationContext(), "welcome " + username, Toast.LENGTH_SHORT).show();

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Logging out")
                        .setCancelable(false)
                        .setNegativeButton("no", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setPositiveButton("yes", (dialog, which) -> {
                            SharedPreferences.Editor ed = sp.edit();
                            ed.clear();
                            ed.apply();
                            Intent kk = new Intent(MainActivity.this, login.class);
                            startActivity(kk);
                        })
                        .setTitle("Log Out")
                        .create()
                        .show();
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kk = new Intent(MainActivity.this, OrderDetail.class);
                startActivity(kk);
            }
        });
        lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kk = new Intent(MainActivity.this, LabPage.class);
                startActivity(kk);
            }
        });
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kk = new Intent(MainActivity.this, Medicine.class);
                startActivity(kk);
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kk = new Intent(MainActivity.this, HealthArticle.class);
                startActivity(kk);

            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kk = new Intent(MainActivity.this, FindDoctor.class);
                startActivity(kk);
            }
        });
    }
}