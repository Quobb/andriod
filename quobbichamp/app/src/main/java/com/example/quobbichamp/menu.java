package com.example.quobbichamp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {
    TextView txtmen;
    Button btnpo;
    MenuItem menu;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txtmen = findViewById(R.id.txtmenu);
        btnpo = findViewById(R.id.btnpop);
        Intent jj = getIntent();
        String kk = jj.getStringExtra("user");
        setTitle("Welcome " + kk);

        btnpo.setOnClickListener(v -> {
            PopupMenu pop = new PopupMenu(getApplicationContext(), btnpo);
            pop.getMenuInflater().inflate(R.menu.men_nav, pop.getMenu());
            pop.show();
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.men_nav, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.calcu) {
            Intent kk = new Intent(getApplicationContext(), calculation.class);
            startActivity(kk);
        } else if (itemId == R.id.juice) {
            Intent h = new Intent(getApplicationContext(), JuiceBar.class);
            startActivity(h);
        } else if (itemId == R.id.log) {
            Intent u = new Intent(getApplicationContext(), login.class);
            startActivity(u);
        } else if (itemId == R.id.Detail) {
            Toast.makeText(menu.this, "name : kojo kofi peter john" +
                    "+2333 67989", Toast.LENGTH_SHORT).show();
        }

        return true;
    }


}