package com.example.trial2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button btnExit, btnnew, btncal;
    EditText et1, et2, et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExit = findViewById(R.id.btnExit);
        btncal = findViewById(R.id.btnCal);
        btnnew = findViewById(R.id.btnnew);
        et1 = findViewById(R.id.txtbase);
        et2 = findViewById(R.id.txtheight);
        et3 = findViewById(R.id.txthypo);

        btncal.setEnabled(false);
        btnnew.setEnabled(false);
        et3.setEnabled(false);
        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String strBase = et1.getText().toString();
                    String strHeight = et2.getText().toString();
                    if (strBase.isEmpty() || strHeight.isEmpty()) {
                        // Show a dialog or display a message indicating that a value is required for n1
                        Toast.makeText(MainActivity.this, "Please enter the base to be calculated", Toast.LENGTH_SHORT).show();

                    } else {
                        double n1 = Double.parseDouble(strBase);
                        double n2 = Double.parseDouble(strHeight);
                        double result = Math.sqrt(Math.pow(n1, 2) + Math.pow(n2, 2));
                        @SuppressLint("DefaultLocale") String formattedResult = String.format("%.2f", result);
                        et3.setText(formattedResult);

                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "numeric error", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText("");
                et2.setText("");
                et3.setText("");
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });
        TextWatcher kk = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isbaseEmpty = et1.getText().toString().trim().isEmpty();
                boolean isHeightEmpty = et2.getText().toString().trim().isEmpty();
                btncal.setEnabled(!(isbaseEmpty || isHeightEmpty));
                btnnew.setEnabled(!(isbaseEmpty || isHeightEmpty));


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        et1.addTextChangedListener(kk);
        et2.addTextChangedListener(kk);

    }


}



