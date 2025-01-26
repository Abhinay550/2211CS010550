package com.example.assignment1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // References to UI components
        EditText editTextFacultyName = findViewById(R.id.editTextFacultyName);
        AutoCompleteTextView autoCompleteDesignation = findViewById(R.id.autoCompleteDesignation);
        EditText editTextDOJ = findViewById(R.id.editTextDOJ);
        RadioGroup radioGroupGender = findViewById(R.id.radioGroupGender);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // Set up Designation dropdown
        String[] designations = {"Professor", "Assistant Professor", "Associate Professor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, designations);
        autoCompleteDesignation.setAdapter(adapter);

        // Set up DatePicker for Date of Joining
        editTextDOJ.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        String doj = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        editTextDOJ.setText(doj);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Submit Button Click
        btnSubmit.setOnClickListener(v -> {
            String facultyName = editTextFacultyName.getText().toString();
            String designation = autoCompleteDesignation.getText().toString();
            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
            String gender = selectedGenderId != -1 ? ((RadioButton) findViewById(selectedGenderId)).getText().toString() : "Male";
            String doj = editTextDOJ.getText().toString();

            // Validation
            if (facultyName.isEmpty() || designation.isEmpty() || doj.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pass data to SecondActivity
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("FacultyName", facultyName);
            intent.putExtra("Designation", designation);
            intent.putExtra("Gender", gender);
            intent.putExtra("DOJ", doj);
            startActivity(intent);
        });
    }
}