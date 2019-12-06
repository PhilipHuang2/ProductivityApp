package com.example.productivityapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class CalenderActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView myDate;
    private Button saveDataButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        calendarView = findViewById(R.id.calenderView);
        myDate = findViewById(R.id.myDate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth +  "/" + year;
                myDate.setText(date);
            }
        });

        saveDataButton = findViewById(R.id.saveDate);
        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
                myDate.setText("Item has been added.");

            }
        });
    }

}
