package com.example.quobii;

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

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {
    TextView txtmen;
    Button btnpo;


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
        switch (item.getItemId()) {
            case (R.id.calcu):
                Intent kk = new Intent(getApplicationContext(), calculation.class);
                startActivity(kk);
                break;
            case (R.id.juice):
                Intent h = new Intent(getApplicationContext(), JuiceBar.class);
                startActivity(h);
                break;
            case R.id.log:
                Intent u = new Intent(getApplicationContext(), login.class);
                startActivity(u);
                break;
            case R.id.Detail:
                Toast.makeText(menu.this, "name : kojo kofi peter john" +
                        "+2333 67989", Toast.LENGTH_SHORT).show();
                break; // Don't forget to add the break statement here
        }
        return true;
    }


}