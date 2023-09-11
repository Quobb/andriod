package com.example.assignment_gpa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnExit, btnCalculate, btnNew;
    EditText EditCrediScore, editCredit;
    EditText editDisplay;
    RadioButton radioDiploma, radioDegree;
    RadioGroup radioGroup;
    double cumulativeCreditHours = 0;
    double cumulativeTotalPoints = 0;
    boolean lastScoreAdded = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.Buttn_Add);
        btnExit = findViewById(R.id.Buttn_Exit);
        btnCalculate = findViewById(R.id.Buttn_Calculate);
        btnNew = findViewById(R.id.Buttn_new);
        EditCrediScore = findViewById(R.id.E_grade);
        editCredit = findViewById(R.id.E_credit_hours);
        editDisplay = findViewById(R.id.Edisplay);
        radioDegree = findViewById(R.id.R_bachelor);
        radioDiploma = findViewById(R.id.R_Diploma);
        radioGroup = findViewById(R.id.R_Group);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hideFields();
        

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean scores = editCredit.getText().toString().trim().isEmpty();
                boolean creditscore = EditCrediScore.getText().toString().trim().isEmpty();
                if(!scores && !creditscore){
                handleAddButtonClick();
                disableRadioButtons();
                clearFields();
                hideRadioGroup();
                btnCalculate.setEnabled(true);}else{
                    Toast.makeText(getApplicationContext(),"input details to be added",Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        // Calculate button click listener
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double score = Double.parseDouble(EditCrediScore.getText().toString());
                int creditHours = Integer.parseInt(editCredit.getText().toString());
                RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                if (selectedRadioButton == radioDegree) {
                    if (score >= 88.0) {
                        score = 5.0;
                    } else if (score >= 76.0) {
                        score = 4.0;
                    } else if (score>= 70.0) {
                        score = 3.5;
                    } else if (score >= 67) {
                        score= 3.0;
                    } else {
                        score = 2.0;
                    }
                } else if (selectedRadioButton == radioDiploma) {
                    if (score >= 88.0) {
                        score = 5.0;
                    } else if (score >= 76.0) {
                        score = 4.0;
                    } else if (score>= 70.0) {
                        score = 3.5;
                    } else if (score >= 67) {
                        score= 3.0;
                    } else {
                        score = 2.0;
                    }
                }
//        double gradePoint = calculateGradePoint(score);

                double courseTotalPoints = score * creditHours;
                cumulativeTotalPoints += courseTotalPoints;
                cumulativeCreditHours += creditHours;

                EditCrediScore.setText("");
                editCredit.setText("");
                EditCrediScore.requestFocus();

                disableRadioButtons();
                enableCalculateButton(true);
                lastScoreAdded = true;
                clearFields();
                btnNew.setEnabled(true);
            }
        });
        EditCrediScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean scores = EditCrediScore.getText().toString().trim().isEmpty();
                if (!scores) {
                    try {
                        String myscore = EditCrediScore.getText().toString();
                        double score = Double.parseDouble(myscore);
                        if (score < 0 || score > 100) {
                            Toast.makeText(getApplicationContext(), "Invalid Exams Score", Toast.LENGTH_SHORT).show();
                            enableAddButton(false);
                            enableCalculateButton(false);
                        } else {
                            showFields();
                            btnAdd.setEnabled(true);
                        }
                    } catch (NumberFormatException e) {
                        // Handle invalid score input
                        enableAddButton(false);
                    }
                } else {
                    enableAddButton(false);
                    enableCalculateButton(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean scores = EditCrediScore.getText().toString().trim().isEmpty();
                if (!scores) {
                    try {
                        String myscore = EditCrediScore.getText().toString();
                        double score = Double.parseDouble(myscore);
                        if (score < 0 || score > 100) {
                            Toast.makeText(getApplicationContext(), "Invalid Exams Score", Toast.LENGTH_SHORT).show();
                            enableAddButton(false);
                            enableCalculateButton(false);
                        } else {
                            showFields();
                            btnAdd.setEnabled(true);
                        }
                    } catch (NumberFormatException e) {
                        // Handle invalid score input
                        enableAddButton(false);
                    }
                } else {
                    enableAddButton(false);
                    enableCalculateButton(false);
                }

            }
        });
        // New button click listener
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetViewsToDefault();

            }
        });

        // Exit button click listener
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
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
        editCredit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean scores = editCredit.getText().toString().trim().isEmpty();
                if (!scores) {
                    try {
                        String myscore = editCredit.getText().toString();
                        double creditHours = Double.parseDouble(myscore);
                        if (creditHours < 1 || creditHours > 6) {
                            Toast.makeText(getApplicationContext(), "Invalid Credit Hours,Credit hours should be between 1 - 6", Toast.LENGTH_SHORT).show();
                            btnAdd.setEnabled(false);
                            btnCalculate.setEnabled(false);
                        } else {
                            showFields();
                            btnAdd.setEnabled(true);
                        }
                    } catch (NumberFormatException e) {
                        // Handle invalid credit hours input
                        enableAddButton(false);
                    }
                } else {
                    enableAddButton(false);
                    enableCalculateButton(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean scores = editCredit.getText().toString().trim().isEmpty();
                if (!scores) {
                    try {
                        String myscore = editCredit.getText().toString();
                        double creditHours = Double.parseDouble(myscore);
                        if (creditHours < 1 || creditHours > 6) {
                            Toast.makeText(getApplicationContext(), "Invalid Credit Hours,Credit hours should be between 1 - 6", Toast.LENGTH_SHORT).show();
                            btnAdd.setEnabled(false);
                            btnCalculate.setEnabled(false);
                        } else {
                            showFields();
                            btnAdd.setEnabled(true);
                        }
                    } catch (NumberFormatException e) {
                        // Handle invalid credit hours input
                        enableAddButton(false);
                    }
                } else {
                    enableAddButton(false);
                    enableCalculateButton(false);
                }
            }
        });
    }


    public void handleCalculateButtonClick() {
        if (lastScoreAdded) {
            showConfirmDialog();
        } else {
            calculateAndDisplayGPA();
        }
    }

    public void showConfirmDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Do you want to include the last score in calculations?")
                .setCancelable(false)
                .setNegativeButton("No", (dialog, which) -> {
                    enableCalculateButton(false);
                })
                .setPositiveButton("Yes", (dialog, which) -> {
                    calculateAndDisplayGPA();
                })
                .setTitle("Confirm Last Score")
                .create()
                .show();
    }
    public void handleAddButtonClick() {

    }
    public void calculateAndDisplayGPA() {
        double finalGPA = cumulativeTotalPoints / cumulativeCreditHours;
        RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String classification = "";
        if(radioDiploma.isChecked() || radioDegree.isChecked()){
            if (selectedRadioButton == radioDiploma) { 
                if (finalGPA  >= 4.00) {
                    classification = "First class Honour";
                } else if (finalGPA  >= 3.00) {
                    classification = "Second class Honour";
                } else if (finalGPA  >= 2.00) {
                    classification = "Second class Lower";
                } else if (finalGPA  >= 1.50) {
                    classification = "Pass";
                } else {
                    classification = "Fail";
                }
            } else if (selectedRadioButton == radioDegree) {
                if (finalGPA  >= 3.75) {
                    classification = "First class Honour";
                } else if (finalGPA  >= 3.35) {
                    classification = "Second class Honour";
                } else if (finalGPA  >= 2.51) {
                    classification = "Second class Lower";
                } else if (finalGPA  >= 2.01) {
                    classification = "Third class";
                } else if (finalGPA  >= 1.00) {
                    classification = "Pass";
                } else {
                    classification = "Fail";
                }
            } else {
                classification = "No classification available"; // Handle other cases if necessary
            }
            editDisplay.setText(String.format("Your final GPA: %.2f\n%s", finalGPA, classification));
        }


        showResultsTextView();
        enableCalculateButton(false);
    }

    public void enableAddButton(boolean enabled) {
        btnAdd.setEnabled(enabled);
    }

    public void enableCalculateButton(boolean enabled) {
        btnCalculate.setEnabled(enabled);
    }



    public void showResultsTextView() {
        editDisplay.setVisibility(View.VISIBLE);
    }

    public void resetViewsToDefault() {

//        getSupportActionBar().hide();
        btnNew.setEnabled(false);
        // Set default program selection
        radioDiploma.setChecked(true);
        radioDegree.setChecked(false);

        // Hide results text view
        editDisplay.setVisibility(View.INVISIBLE);

        // Hide the calculate button
        btnCalculate.setVisibility(View.INVISIBLE);

        // Enable the Add button initially
        enableAddButton(false);
        enableCalculateButton(false);
        enableRadioButtons();
        radioGroup.setVisibility(View.VISIBLE);

        // Clear input fields
        clearFields();
        // Set focus on the score text view
        EditCrediScore.requestFocus();

        // Enable program options
        enableRadioButtons();
    }


        public void enableRadioButtons() {
            radioDiploma.setEnabled(true);
            radioDegree.setEnabled(true);
        }

        public void clearFields() {
            EditCrediScore.setText("");
            editCredit.setText("");
            editDisplay.setText("");
        }
        public void hideRadioGroup() {
            radioGroup.setVisibility(View.INVISIBLE);
        }



    public void disableRadioButtons() {
            radioDiploma.setEnabled(false);
            radioDegree.setEnabled(false);
        }

        public void hideFields () {
            btnNew.setVisibility(View.INVISIBLE);
            btnAdd.setVisibility(View.INVISIBLE);
            btnCalculate.setVisibility(View.INVISIBLE);
            editDisplay.setVisibility(View.INVISIBLE);
            btnNew.setEnabled(false);
            btnAdd.setEnabled(false);
            radioDegree.setChecked(true);
        }

        public void showFields () {
            btnNew.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
            btnCalculate.setVisibility(View.VISIBLE);
            editDisplay.setVisibility(View.VISIBLE);
        }



}
