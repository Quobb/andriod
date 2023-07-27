package com.example.quobii;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.*;


public class MainActivity extends AppCompatActivity {
    Button btnExit, btnnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void hypo(View v){
        EditText et1 = findViewById(R.id.txtbase);
        EditText et2 = findViewById(R.id.txtheight);
        EditText et3 = findViewById(R.id.txthypo);
        Button btncalculate = findViewById(R.id.btnCal); // Replace 'btnCalculate' with the actual ID of your button.
        try {
            String strBase = et1.getText().toString();
            String strHeight = et2.getText().toString();
            if (strBase.isEmpty()) {
                // Show a dialog or display a message indicating that a value is required for n1
                Toast.makeText(this, "Please enter the base to be calculated", Toast.LENGTH_SHORT).show();
                return;
            }

            if (strHeight.isEmpty()) {
                // Show a dialog or display a message indicating that a value is required for n2
                Toast.makeText(this, "Please enter the height to be calculated", Toast.LENGTH_SHORT).show();
                return;
            }

            double n1 = Double.parseDouble(strBase);
            double n2 = Double.parseDouble(strHeight);
            double result = Math.sqrt(Math.pow(n1,2) + Math.pow(n2,2));
            String formattedResult = String.format("%.2f", result);
            et3.setText(formattedResult);



        }catch (NumberFormatException e){

        }

    }


    public void newcal(View v){
        EditText et1 = (EditText)findViewById(R.id.txtbase);
        EditText et2 = (EditText)findViewById(R.id.txtheight);
        EditText et3 = (EditText)findViewById(R.id.txthypo);
        et1.setText("");
        et2.setText("");
        et3.setText("");
    }
    public void exit(View v){
        System.exit(0);
    }
    public void disable(View v){
        EditText et1 = (EditText)findViewById(R.id.txtbase);
        EditText et2 = (EditText)findViewById(R.id.txtheight);
        Button  button = findViewById(R.id.btnCal);
        String strBase = et1.getText().toString();
        String strHeight = et2.getText().toString();

        if (strBase.isEmpty() && strHeight.isEmpty()) {
            button.setEnabled(false);
        }else{
            button.setEnabled(true);
        }

    }
    public  void disabletxt(View v){
        EditText et3 = (EditText)findViewById(R.id.txthypo);
        et3.setEnabled(false);
    }
    public void onClick(View v){
       System.exit(1);
    }

}