package com.example.trialler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void exit(View v){
        System.exit(1);
    }
}