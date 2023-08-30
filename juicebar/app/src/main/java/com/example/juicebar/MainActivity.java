package com.example.juicebar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    RadioButton btnsel, btnfru, btnveg, btnpro, btnstraw, btnweat;
    EditText txtPrice, txtQuantity, txtCost, txtSubtotal, txtTax, txtAmount;
    CheckBox chkenergy, chklabies;
    Button btnOrder, btnComp, btnReport, btnNew, btnExit;
    RadioGroup radiogroup;
    FloatingActionButton floats;
    double Price,quantity,cost,tax,extra,subtotal=0,amountdue=0,totaltax;
    DecimalFormat nf = new DecimalFormat("0.00");
    NumberFormat kk = NumberFormat.getCurrencyInstance();
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.activity_main);

        // Assigning variables to UI elements
        Initialiazation();


        // Disable buttons and text fields
        disableAllMajorButoon();



        // Set tags for radio buttons
        radiouttonTag();


        floats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                programmerDetail();
            }
        });

        chklabies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCost();

            }
        });
        chkenergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCost();
            }
        });

        // Handle radio button selection change
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);
                int selectedValue = (int) selectedRadioButton.getTag();
                txtPrice.setText(Integer.toString(selectedValue));
            }
        });

        // Handle "Exit" button click
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleExit();
            }
        });

        // Handle "New" button click
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllFields(); // Call the function to clear all text fields
                clearcheck();
            }
        });

        // Handle "Compute" button click
        btnComp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(radiogroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(MainActivity.this,"select a product",Toast.LENGTH_SHORT).show();
                }else {
                    ischeckladies();
                    ischeckengy();
                }

            }
        });

        // Handle "Order" button click
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Do you want add ?")
                        .setCancelable(false)
                        .setNegativeButton("no",(dialog, which) ->{
                            clearAllFields();
                        } )
                        .setPositiveButton("yes",(dialog, which) ->{
                            btnReport.setEnabled(true);
                            btnComp.setEnabled(false);
                            btnOrder.setEnabled(false);

                        } )
                        .setTitle("Adding")
                        .create()
                        .show();

            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDetail();

            }
        });

        // TextWatcher to enable/disable "New" button based on input
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean quantity = txtQuantity.getText().toString().trim().isEmpty();
                btnNew.setEnabled(!(quantity));
                btnComp.setEnabled(!(quantity));
            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean quantity = txtQuantity.getText().toString().trim().isEmpty();
                btnNew.setEnabled(!(quantity));
//                btnComp.setEnabled(!(quantity));
                ischeckengy();
                ischeckladies();

            }
        };
        txtQuantity.addTextChangedListener(textWatcher);


    }
    private void updateCost() {
        // Calculate the cost based on user input
        cost();

        // Adjust cost based on checkboxes
        if (chkenergy.isChecked()) {
            Price += 5.0;
        }

        if (chklabies.isChecked()) {
            Price += 5.0;
        }

        // Update UI fields
        txtPrice.setText(kk.format(cost));

        tax = cost * 0.35;
        txtTax.setText(kk.format(tax));

        extra = tax + cost;
        txtAmount.setText(kk.format(extra));
    }

    public  void ischeckladies(){
        if(chklabies.isChecked()){
            boolean quantity = txtQuantity.getText().toString().trim().isEmpty();

            btnOrder.setEnabled(!(quantity));
            updateCost();
            allTextfieldDisable();
        }else{
            boolean quantity = txtQuantity.getText().toString().trim().isEmpty();

            btnOrder.setEnabled(!(quantity));
            cost();
            allTextfieldDisable();
        }
    }
    public void ischeckengy(){
        if(chkenergy.isChecked()){
            boolean quantity = txtQuantity.getText().toString().trim().isEmpty();

            btnOrder.setEnabled(!(quantity));
            updateCost();
            allTextfieldDisable();
        }else{
            boolean quantity = txtQuantity.getText().toString().trim().isEmpty();

            btnOrder.setEnabled(!(quantity));
            cost();
            allTextfieldDisable();
        }
    }

    public void cost(){
        try {
            Price = Double.parseDouble(txtPrice.getText().toString());
            quantity = Double.parseDouble(txtQuantity.getText().toString());
            subtotal = Price;
            txtSubtotal.setText(kk.format(subtotal));
            cost = Price * quantity;
            txtCost.setText(kk.format(cost));
            tax = cost * 0.35;
            txtTax.setText(kk.format(tax));
            extra = tax + cost;
            txtAmount.setText(kk.format(extra));

        } catch (NumberFormatException e) {
            // Handle parsing errors here
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
//
    private void clearAllFields() {
        txtCost.setText("");
        txtAmount.setText("");
        txtPrice.setText("");
        txtTax.setText("");
        txtSubtotal.setText("");
        txtQuantity.setText(""); // Clear the quantity field

    // You can add more fields to be cleared here if needed
}

   public void clearcheck(){
       chklabies.setChecked(false);
       chkenergy.setChecked(false);
      // radiogroup.clearCheck(); // Clear the checked state of the radio group
       txtQuantity.setEnabled(true);
       chkenergy.setEnabled(true);
       chklabies.setEnabled(true);
       btnOrder.setEnabled(false);
       btnReport.setEnabled(false);
       btnComp.setEnabled(true);
       // Enable the radio buttons but do not set any of them as checked
       btnsel.setEnabled(true);
       btnstraw.setEnabled(true);
       btnpro.setEnabled(true);
       btnfru.setEnabled(true);
       btnweat.setEnabled(true);
   }

   public void handleDetail(){
       AlertDialog.Builder kk = new AlertDialog.Builder(MainActivity.this); // Initialize AlertDialog.Builder instance

       kk.setMessage("Price : "+ txtPrice.getText().toString().trim() +"\n\n"+
                       "Quantity : "+ txtQuantity.getText().toString().trim() + "\n\n" +
                       "Cost : "+ txtCost.getText().toString().trim() +"\n\n"+
                       "Subtotal : "+txtSubtotal.getText().toString().trim() +"\n\n"+
                       " Tax : "+txtTax.getText().toString().trim() +"\n\n"+
                       " AmountDue : "+txtAmount.getText().toString().trim())
               .setCancelable(false)
               .setPositiveButton("Yes", (dialog, which) -> {
                   dialog.dismiss();
               });

       AlertDialog alert = kk.create(); // Create the AlertDialog

       alert.setTitle("Product Summary"); // Set the title for the AlertDialog

       alert.show();

   }

   public void HandleExit(){
       AlertDialog.Builder kk = new AlertDialog.Builder(MainActivity.this); // Initialize AlertDialog.Builder instance

       kk.setMessage("Exiting")
               .setCancelable(false)
               .setNegativeButton("NO", ((dialog, which) -> {
                   dialog.dismiss();
               }))
               .setPositiveButton("Yes", (dialog, which) -> {
                 finish();
               });

       AlertDialog alert = kk.create(); // Create the AlertDialog

       alert.setTitle("exiting Application"); // Set the title for the AlertDialog

       alert.show();

   }

   public void programmerDetail(){
       AlertDialog.Builder kk = new AlertDialog.Builder(MainActivity.this); // Initialize AlertDialog.Builder instance

       kk.setMessage("name : Tsonyake Elikplim Adam"+
                       "\n\n"+"Index nov: 0321080041"+
                       "\n\n"+"Phone no : +233 552 915 020")
               .setCancelable(false)
               .setPositiveButton("Yes", (dialog, which) -> {
//                            dialog.dismiss();
                   Intent ll = new Intent(getApplicationContext(), Gpa_calculator.class);
                   startActivity(ll);
               });

       AlertDialog alert = kk.create(); // Create the AlertDialog

       alert.setTitle("Programmer Details"); // Set the title for the AlertDialog

       alert.show();
   }

   public void Initialiazation(){
       btnsel = findViewById(R.id.no_selc);
       btnfru = findViewById(R.id.btn_fruit);
       btnveg = findViewById(R.id.btn_veggie);
       btnpro = findViewById(R.id.btn_prom);
       btnstraw = findViewById(R.id.btn_berry);
       btnweat = findViewById(R.id.btn_wheat);
       txtPrice = findViewById(R.id.Etext_Price);
       txtQuantity = findViewById(R.id.Etext_Quant);
       txtCost = findViewById(R.id.Etext_cost);
       txtAmount = findViewById(R.id.Etext_amount);
       txtSubtotal = findViewById(R.id.Etext_sub);
       txtTax = findViewById(R.id.Etext_Tax);
       chkenergy = findViewById(R.id.chkenerg);
       chklabies = findViewById(R.id.checkladies);
       btnOrder = findViewById(R.id.Btn_Oder);
       btnComp = findViewById(R.id.Btn_Add);
       btnReport = findViewById(R.id.Btn_Sum);
       btnNew = findViewById(R.id.Btn_new);
       btnExit = findViewById(R.id.Btn_exit);
       radiogroup = findViewById(R.id.R_group);
       floats = findViewById(R.id.F_float);
   }

   public void radiouttonTag(){
       btnsel.setTag(0);
       btnfru.setTag(10);
       btnveg.setTag(15);
       btnpro.setTag(20);
       btnstraw.setTag(24);
       btnweat.setTag(24);
//       chkenergy.setTag(5);
//       chklabies.setTag(5);
   }


   public void allTextfieldDisable(){
       txtQuantity.setEnabled(false); // Disable quantity input field
       chkenergy.setEnabled(false);   // Disable checkboxes
       chklabies.setEnabled(false);
       btnsel.setEnabled(false);      // Disable radio buttons
       btnstraw.setEnabled(false);
       btnpro.setEnabled(false);
       btnfru.setEnabled(false);
       btnweat.setEnabled(false);
   }

   public void disableAllMajorButoon(){
       btnNew.setEnabled(false);
       btnReport.setEnabled(false);
       btnOrder.setEnabled(false);
   }
}