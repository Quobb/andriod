package com.example.assignment_gpa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.*;
import android.service.controls.actions.FloatAction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import java.text.DecimalFormat;
import java.util.*;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Bar extends AppCompatActivity {
    RadioButton Diploma,Degree;
    Button BtnNew,BtnCal,BtnAdd,BtnExit;
    EditText ScoreET,CreditEt;
    TextView Result;
    FloatingActionButton detail;
    double GPA=0 , TPoints = 0,CHours=0;\
    RadioGroup radio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        BtnNew = findViewById(R.id.btnNew);
        BtnAdd = findViewById(R.id.btnAdd);
        BtnCal = findViewById(R.id.btnCalculate);
        BtnExit = findViewById(R.id.btnExit);
        ScoreET = findViewById(R.id.txtScore);
        CreditEt = findViewById(R.id.txtChours);
        Result = findViewById(R.id.txtresults);
        detail = findViewById(R.id.InfoFloat);
        Diploma = findViewById(R.id.rbtBTech);
        Degree = findViewById(R.id.rbtHND);
        radio = findViewById(R.id.rbtgroup);

        Diploma.setChecked(true);
        Result.setVisibility(View.INVISIBLE);
        BtnCal.setVisibility(View.INVISIBLE);


        ScoreET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Boolean score = ScoreET.getText().toString().trim().isEmpty();
                if(!score){
                    double scores =  Double.parseDouble(ScoreET.getText().toString());
                    if(scores < 0 || scores > 100){
                        Toast.makeText(getApplicationContext(), "Invalid score", Toast.LENGTH_SHORT).show();
                        BtnAdd.setEnabled(false);
                        ScoreET.setFocusable(true);
                    }else{
                        BtnAdd.setEnabled(true);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        CreditEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Boolean Credit = CreditEt.getText().toString().trim().isEmpty();
                if(!Credit){
                    double credits =  Double.parseDouble(CreditEt.getText().toString());
                    if(credits < 0 || credits > 6){
                        Toast.makeText(getApplicationContext(), "Invalid Credit Hours,Credit should be between 1- 6", Toast.LENGTH_SHORT).show();
                        BtnAdd.setEnabled(false);
                        CreditEt.setFocusable(true);
                    }else{
                        BtnAdd.setEnabled(true);
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        BtnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreET.setText("");
                CreditEt.setText("");
                BtnAdd.setEnabled(true);
                BtnCal.setEnabled(true);
                BtnCal.setVisibility(View.INVISIBLE);
                Result.setVisibility(View.INVISIBLE);
                ScoreET.setFocusable(true);
                BtnNew.setEnabled(false);
                radio.setEnabled(true);
            }
        });
        BtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Bar.this)
                        .setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setNegativeButton("No", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setPositiveButton("Yes", (dialog, which) -> {
                            finish();
                        })
                        .setTitle("Exit")
                        .create()
                        .show();
            }
        });
        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ScoreET.getText().length() !=0 && ScoreET.getText().length() !=0){   // 1 mark
                    int hours = Integer.parseInt(ScoreET.getText().toString());
                    double mark = Double.parseDouble(ScoreET.getText().toString());    // 1 mark
                    double GPoint = CheckGradePoint(mark);
                    CHours += hours;
                    TPoints += (GPoint * hours);
                    ScoreET.setText("");
                    ScoreET.setText("");
                    ScoreET.requestFocus();
                    BtnCal.setVisibility(View.VISIBLE);
                    if (Diploma.isChecked()){
                        Degree.setEnabled(false);
                    }else{
                        Diploma.setEnabled(false);
                    }
                }else{
                    Toast.makeText(Bar.this, "All fields are mandatory", Toast.LENGTH_LONG).show();
                }
            }
        });
        BtnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ScoreET.getText().length() != 0 && ScoreET.getText().length() != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Bar.this);
                    builder.setMessage("Do you want to add this score?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int hours = Integer.parseInt(CreditEt.getText().toString());
                                    double mark = Double.parseDouble(ScoreET.getText().toString());
                                    double GPoint = CheckGradePoint(mark);
                                    CHours += hours;
                                    TPoints += (GPoint * hours);
                                    CreditEt.setText("");
                                    ScoreET.setText("");
                                    ScoreET.requestFocus();
                                    //btnAdd.setOnClickListener;
                                    CalDisplay();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    CalDisplay();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Adding...");
                    alert.show();
                } else {
                    CalDisplay();
                }
            }
        });

    }
    public double CheckGradePoint(double Score) {
        if (Diploma.isChecked()) {
            double value = Double.parseDouble(ScoreET.getText().toString());
            if (value >= 85)
                return 5.00;
            else if (value >= 80)
                return 4.50;
            else if (value >= 75)
                return 4.00;
            else if (value >= 70)
                return 3.50;
            else if (value >= 65)
                return 3.00;
            else if (value >= 60)
                return 2.50;
            else if (value >= 55)
                return 2.00;
            else if (value >= 50)
                return 1.50;
            else
                return 0.00;
        } else {
            double value = Double.parseDouble(ScoreET.getText().toString());
            if (value >= 80)
                return 4.00;
            else if (value >= 75)
                return 3.50;
            else if (value >= 70)
                return 3.00;
            else if (value >= 65)
                return 2.50;
            else if (value >= 60)
                return 2.00;
            else if (value >= 55)
                return 1.50;
            else if (value >= 50)
                return 1.00;
            else if (value >= 45)
                return 1.00;
            else
                return 0.00;
        }
    }
    public void CalDisplay(){
        DecimalFormat df = new DecimalFormat("0.00");
        GPA = TPoints/CHours;
        String GClass;
        if (Diploma.isChecked()){
            if (GPA >= 4.00)
                GClass = "First Class";
            else if (GPA >= 3.0)
                GClass = "Second Class (Upper Division)";
            else if (GPA >= 2.0)
                GClass = "Second  Class (Lower Division)";
            else if (GPA >= 1.50)
                GClass = "Pass";
            else
                GClass = "Fail";
        }else{
            if (GPA >= 3.75)
                GClass = "First Class";
            else if (GPA >= 3.35)
                GClass = "Second Class (Upper Division)";
            else if (GPA >= 2.51)
                GClass = "Second  Class (Lower Division)";
            else if (GPA >= 2.01)
                GClass = "Third Class";
            else if (GPA >= 1.00)
                GClass = "Pass";
            else
                GClass = "Fail";
        }
        Result.setText("Your Final GPA is "+ df.format(GPA) + "\nClass - " + GClass);
        Result.setVisibility(View.VISIBLE);
        BtnAdd.setEnabled(false);
        BtnCal.setEnabled(false);
    }
}
}