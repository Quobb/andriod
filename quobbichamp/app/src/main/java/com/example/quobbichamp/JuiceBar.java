package com.example.quobbichamp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class JuiceBar extends AppCompatActivity {
 RadioButton btnsel,btnfru,btnveg,btnpro,btnstraw,btnweat;
 EditText txtp,txtQ,txtC,txtsub,txttax,txtamount;
 CheckBox chkenergy,chklabies;
 Button btnOrder,btnComp,btnReport,btnNew,btnExit;
 RadioGroup radiogroup;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juice_bar);
        btnsel = findViewById(R.id.no_selc);
        btnfru = findViewById(R.id.btn_fruit);
        btnveg = findViewById(R.id.btn_veggie);
        btnpro =findViewById(R.id.btn_prom);
        btnstraw = findViewById(R.id.btn_berry);
        btnweat = findViewById(R.id.btn_wheat);
        txtp = findViewById(R.id.Etext_Price);
        txtQ = findViewById(R.id.Etext_Quant);
        txtC =findViewById(R.id.Etext_cost);
        txtsub =findViewById(R.id.Etext_sub);
        txttax = findViewById(R.id.Etext_Tax);
        chkenergy = findViewById(R.id.chkenerg);
        chklabies = findViewById(R.id.checkladies);
        btnOrder = findViewById(R.id.Btn_Oder);
        btnComp = findViewById(R.id.Btn_Add);
        btnReport = findViewById(R.id.Btn_Sum);
        btnNew = findViewById(R.id.Btn_new);
        btnExit = findViewById(R.id.Btn_exit);
        radiogroup = findViewById(R.id.R_group);


        btnNew.setEnabled(false);
        btnReport.setEnabled(false);
        btnOrder.setEnabled(false);
        txttax.setEnabled(false);
        txtsub.setEnabled(false);
        txtp.setEnabled(false);
        txtC.setEnabled(false);
        txtamount.setEnabled(false);

        btnsel.setTag(5);
        btnfru.setTag(10);
        btnveg.setTag(15);

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton selectedRadioButton = findViewById(checkedId);
                int selectedValue = (int) selectedRadioButton.getTag();
                txtp.setText(Integer.toString(selectedValue));

            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtamount.setText("");
                txtp.setText("");
                txttax.setText("");
                txtsub.setText("");
            }
        });

        btnComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean quantity = txtQ.getText().toString().trim().isEmpty();
                btnOrder.setEnabled(!(quantity));

            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReport.setEnabled(true);
            }
        });
        TextWatcher kk = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean quantity = txtQ.getText().toString().trim().isEmpty();
                btnNew.setEnabled(!(quantity));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        txtQ.addTextChangedListener(kk);

    }
}