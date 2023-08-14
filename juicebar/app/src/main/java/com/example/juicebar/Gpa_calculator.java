package com.example.juicebar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.*;

public class Gpa_calculator extends AppCompatActivity {
    private Button calculateButton, exitButton, addButton, newButton;
    private RadioGroup degreeRadioGroup;
    private RadioButton diplomaRadioButton, bachelorRadioButton;
    private EditText creditEditText, gradeEditText,displayTextView;
//    private TextView displayTextView;
    private int cumulativeCreditHours = 0;
    private double cumulativeTotalPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_calculator);

        initializeViews();

        calculateButton.setEnabled(false);
        newButton.setEnabled(false);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddButtonClick();
            }
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNewButtonClick();
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCalculateButtonClick();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitConfirmationDialog();
            }
        });

        gradeEditText.addTextChangedListener(gradeTextWatcher);
        creditEditText.addTextChangedListener(creditTextWatcher);
    }

    private void initializeViews() {
        calculateButton = findViewById(R.id.btn_Calculate);
        exitButton = findViewById(R.id.btn_Exit);
        addButton = findViewById(R.id.btn_Add);
        newButton = findViewById(R.id.btn_new);
        diplomaRadioButton = findViewById(R.id.R_Diploma);
        bachelorRadioButton = findViewById(R.id.R_bachelor);
        degreeRadioGroup = findViewById(R.id.R_group);
        creditEditText = findViewById(R.id.E_credit_hours);
        gradeEditText = findViewById(R.id.E_grade);
        displayTextView = findViewById(R.id.E_display);

        diplomaRadioButton.setChecked(true);
        displayTextView.setVisibility(View.INVISIBLE);
        calculateButton.setVisibility(View.INVISIBLE);
        newButton.setVisibility(View.INVISIBLE);
    }

    private void handleAddButtonClick() {
        if (creditEditText.getText().length() != 0 && gradeEditText.getText().length() != 0) {
            int credit = Integer.parseInt(creditEditText.getText().toString());
            double gradePoint = calculateGradePoint();
            double totalPoints = credit * gradePoint;

            cumulativeTotalPoints += totalPoints;
            cumulativeCreditHours += credit;

            creditEditText.setText("");
            gradeEditText.setText("");
            calculateButton.setEnabled(true);
        } else {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleNewButtonClick() {
        diplomaRadioButton.setChecked(true);
        bachelorRadioButton.setChecked(true);
        creditEditText.setText("");
        gradeEditText.setText("");
        displayTextView.setText("");
        displayTextView.setVisibility(View.INVISIBLE);
        calculateButton.setVisibility(View.INVISIBLE);
        newButton.setVisibility(View.INVISIBLE);
    }

    private void handleCalculateButtonClick() {
        displayResults();
        displayTextView.setVisibility(View.VISIBLE);
        calculateButton.setEnabled(false);
        addButton.setEnabled(false);
        newButton.setVisibility(View.VISIBLE);
    }

    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Exiting")
                .setCancelable(false)
                .setNegativeButton("NO", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setPositiveButton("Yes", (dialog, which) -> {
                    finish();
                })
                .setTitle("Exiting Application")
                .create()
                .show();
    }

    private final TextWatcher gradeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String grade = gradeEditText.getText().toString();
            if (diplomaRadioButton.isChecked()) {
                if ("A+".equals(grade) || "A".equals(grade) || "B+".equals(grade) ||
                        "B".equals(grade) || "C+".equals(grade) || "C".equals(grade) ||
                        "D+".equals(grade) || "D".equals(grade) || "F".equals(grade)) {
                    // Valid grade
                } else {
                    Toast.makeText(Gpa_calculator.this, "Invalid Grade", Toast.LENGTH_SHORT).show();
                    gradeEditText.setText("");
                }
            } else {
                if ("E".equals(grade) || "A".equals(grade) || "B+".equals(grade) ||
                        "B".equals(grade) || "C+".equals(grade) || "C".equals(grade) ||
                        "D+".equals(grade) || "D".equals(grade) || "F".equals(grade)) {
                    // Valid grade
                } else {
                    Toast.makeText(Gpa_calculator.this, "Invalid Grade", Toast.LENGTH_SHORT).show();
                    gradeEditText.setText("");
                }
            }
        }
    };

    private final TextWatcher creditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String creditStr = creditEditText.getText().toString();
            if (!creditStr.isEmpty()) {
                int credit = Integer.parseInt(creditStr);
                if (credit > 1 && credit < 6) {
                    // Valid credit hours
                } else {
                    Toast.makeText(Gpa_calculator.this, "Invalid credit", Toast.LENGTH_SHORT).show();
                    creditEditText.setText("");
                }
            }
        }
    };

    private double calculateGradePoint() {
        String grade = gradeEditText.getText().toString();
        if (diplomaRadioButton.isChecked()) {
            switch (grade) {
                case "A+":
                    return 5.0;
                case "A":
                    return 4.5;
                case "B+":
                    return 4.0;
                case "B":
                    return 3.5;
                case "C+":
                    return 3.0;
                case "C":
                    return 2.5;
                case "D+":
                    return 2.0;
                case "D":
                    return 1.5;
                default:
                    return 1.0;
            }
        } else { // Assuming bachelorRadioButton is selected
            switch (grade) {
                case "A":
                    return 4.5;
                case "B+":
                    return 4.0;
                case "B":
                    return 3.5;
                case "C+":
                    return 3.0;
                case "C":
                    return 2.5;
                case "D+":
                    return 2.0;
                case "D":
                    return 1.5;
                case "E":
                    return 1.3;
                default:
                    return 1.0;
            }
        }
    }

    private void displayResults() {
        double cumulativeGPA = cumulativeTotalPoints / cumulativeCreditHours;
        String classification;

        if (diplomaRadioButton.isChecked()) {
            if (cumulativeGPA >= 4.0) {
                classification = "First class Honour";
            } else if (cumulativeGPA >= 3.0) {
                classification = "Second class Honour";
            } else if (cumulativeGPA >= 2.0) {
                classification = "Second class Lower";
            } else if (cumulativeGPA >= 1.5) {
                classification = "Pass";
            } else {
                classification = "Fail";
            }
        } else { // Assuming bachelorRadioButton is selected
            if (cumulativeGPA >= 3.75) {
                classification = "First class Honour";
            } else if (cumulativeGPA >= 3.35) {
                classification = "Second class Honour";
            } else if (cumulativeGPA >= 2.51) {
                classification = "Second class Lower";
            } else if (cumulativeGPA >= 1.0) {
                classification = "Pass";
            } else {
                classification = "Fail";
            }
        }

        displayTextView.setText(String.format("Your final GPA: %s\n%s", cumulativeGPA, classification));
    }
}
