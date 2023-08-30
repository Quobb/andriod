package com.example.juicebar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.appwidget.*;

public class Gpa2 extends AppCompatActivity {
    Button btnAdd,btnExit,btnCalculate,btnNew;
    EditText EditGrade,EditCredit,EditDisplay;
    RadioButton RadioDeploma,RadioDegree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa2);

        btnAdd = findViewById(R.id.Buttn_Add);
        btnExit = findViewById(R.id.Buttn_Exit);
        btnCalculate = findViewById(R.id.Buttn_Calculate);
        btnNew = findViewById(R.id.Buttn_new);
        EditGrade = findViewById(R.id.Egrade);
        EditCredit = findViewById(R.id.Ecredit_hours);
        EditDisplay = findViewById(R.id.Edisplay);
        RadioDeploma = findViewById(R.id.Bachelor);
        RadioDegree = findViewById(R.id.Diploma);

        disablevield();
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFeild();
                enableRadioButton();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exithandle();
            }
        });

    }
    public void enableRadioButton(){
        RadioDegree.setEnabled(true);
        RadioDeploma.setEnabled(true);
        RadioDeploma.setChecked(true);
        RadioDegree.setChecked(true);
    }
    public void clearFeild(){
        EditDisplay.setText(" ");
        EditCredit.setText(" ");
        EditGrade.setText(" ");
    }
    public void disablevield(){
        btnNew.setVisibility(View.INVISIBLE);
        btnAdd.setVisibility(View.INVISIBLE);
        btnCalculate.setVisibility(View.INVISIBLE);
        EditDisplay.setVisibility(View.INVISIBLE);
        RadioDegree.setEnabled(false);
        RadioDeploma.setEnabled(false);
        RadioDeploma.setChecked(false);
        RadioDegree.setChecked(false);
    }
    public void exithandle(){
        new AlertDialog.Builder(Gpa2.this)
                .setMessage("closing")
                .setCancelable(false)
                .setNegativeButton("NO",(dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton("yes",(dialog, which) ->{
                    finish();
                } )
                .setTitle("Exit")
                .create()
                .show();
    }
}