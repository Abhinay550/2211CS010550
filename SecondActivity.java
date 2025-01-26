package com.example.assignment1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textViewDetails = findViewById(R.id.textViewDetails);

        // Get data from Intent
        String facultyName = getIntent().getStringExtra("FacultyName");
        String designation = getIntent().getStringExtra("Designation");
        String gender = getIntent().getStringExtra("Gender");
        String doj = getIntent().getStringExtra("DOJ");

        // Calculate Years of Experience
        String experience = calculateExperience(doj);

        // Display details
        String details = "Faculty Name: " + facultyName + "\n"
                + "Designation: " + designation + "\n"
                + "Gender: " + gender + "\n"
                + "Date of Joining: " + doj + "\n"
                + "Years of Experience: " + experience;

        textViewDetails.setText(details);
    }

    private String calculateExperience(String doj) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateOfJoining = sdf.parse(doj);
            Calendar joinDate = Calendar.getInstance();
            joinDate.setTime(dateOfJoining);

            Calendar currentDate = Calendar.getInstance();
            int years = currentDate.get(Calendar.YEAR) - joinDate.get(Calendar.YEAR);

            if (currentDate.get(Calendar.DAY_OF_YEAR) < joinDate.get(Calendar.DAY_OF_YEAR)) {
                years--;
            }

            return String.valueOf(years);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date";
        }
    }
}