package com.example.juicebar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Gpa_calculator extends AppCompatActivity {
    private Button calculateButton, exitButton, addButton, newButton;
    private RadioGroup degreeRadioGroup;
    private RadioButton diplomaRadioButton, bachelorRadioButton;
    private EditText creditEditText, gradeEditText;
    private TextView displayTextView;
    private int cumulativeCreditHours = 0;
    private double cumulativeTotalPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_calculator);

        initializeViews();

        calculateButton.setEnabled(false);
        newButton.setEnabled(false);

        addButton.setOnClickListener(v -> handleAddButtonClick());
        newButton.setOnClickListener(v -> handleNewButtonClick());
        calculateButton.setOnClickListener(v -> handleCalculateButtonClick());
        exitButton.setOnClickListener(v -> showExitConfirmationDialog());

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
                .setNegativeButton("NO", (dialog, which) -> dialog.dismiss())
                .setPositiveButton("Yes", (dialog, which) -> finish())
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
            RadioButton selectedRadioButton = findViewById(degreeRadioGroup.getCheckedRadioButtonId());

            if (!grade.isEmpty()) {
                if (selectedRadioButton == diplomaRadioButton) {
                    if (!isValidDiplomaGrade(grade)) {
                        gradeEditText.setText("");
                        Toast.makeText(Gpa_calculator.this, "Invalid Grade", Toast.LENGTH_SHORT).show();
                    }
                } else if (selectedRadioButton == bachelorRadioButton) {
                    if (!isValidBachelorGrade(grade)) {
                        gradeEditText.setText("");
                        Toast.makeText(Gpa_calculator.this, "Invalid Grade", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

    private boolean isValidDiplomaGrade(String grade) {
        return grade.equals("A+") || grade.equals("A") || grade.equals("B+") ||
                grade.equals("B") || grade.equals("C+") || grade.equals("C") ||
                grade.equals("D+") || grade.equals("D") || grade.equals("F");
    }

    private boolean isValidBachelorGrade(String grade) {
        return grade.equals("A") || grade.equals("B+") || grade.equals("B") ||
                grade.equals("C+") || grade.equals("C") || grade.equals("D+") ||
                grade.equals("D") || grade.equals("E") || grade.equals("F");
    }

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
                if (credit < 1 || credit > 5) {
                    creditEditText.setText("");
                    Toast.makeText(Gpa_calculator.this, "Invalid credit", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private double calculateGradePoint() {
        String grade = gradeEditText.getText().toString();
        RadioButton selectedRadioButton = findViewById(degreeRadioGroup.getCheckedRadioButtonId());

        if (selectedRadioButton == diplomaRadioButton) {
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
        } else if (selectedRadioButton == bachelorRadioButton) {
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
        return 0.0; // Default value if no radio button is selected (error handling)
    }

    private void displayResults() {
        double cumulativeGPA = cumulativeTotalPoints / cumulativeCreditHours;
        String classification;
        RadioButton selectedRadioButton = findViewById(degreeRadioGroup.getCheckedRadioButtonId());

        if (selectedRadioButton == diplomaRadioButton) {
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
        } else if (selectedRadioButton == bachelorRadioButton) {
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
        } else {
            classification = "No classification available"; // Handle other cases if necessary
        }

        displayTextView.setText(String.format("Your final GPA: %.2f\n%s", cumulativeGPA, classification));
    }
}
