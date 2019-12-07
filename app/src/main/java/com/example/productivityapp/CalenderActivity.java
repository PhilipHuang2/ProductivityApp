package com.example.productivityapp;
//Author Philip Huang
import android.content.Intent;
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
    private Button saveDataButton,loadDataButton;
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
                if(!myDate.getText().equals("Choose a Date") && !myDate.getText().equals("Please choose a date to continue."))
                    openJournalEntry();
                else
                    myDate.setText("Please choose a date to continue.");
            }
        });

        loadDataButton = findViewById(R.id.loadDate);
        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!myDate.getText().equals("Choose a Date") && !myDate.getText().equals("Please choose a date to continue."))
                    openJournalLoad();
                else
                    myDate.setText("Please choose a date to continue.");
            }
        });
    }

    public void openJournalEntry()
    {
        String date = myDate.getText().toString();
        int month = 0;
        int day = 0;
        int year = 0;
        int check = 0;
        for (int i = 0; i < date.length(); i++)
        {
            char c = date.charAt(i);
            if(Character.isDigit(c))
            {
                switch(check){
                    case 0 : month = 10 * month + Character.getNumericValue(c); break;
                    case 1: day = 10 * day + Character.getNumericValue(c); break;
                    case 2: year = year * 10 + Character.getNumericValue(c); break;
                }
            }
            else
                check++;
        }
        Intent intent = new Intent(this, JournalEntryActivity.class);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("year", year);
        startActivity(intent);
    }

    public void openJournalLoad()
    {
        String date = myDate.getText().toString();
        int month = 0;
        int day = 0;
        int year = 0;
        int check = 0;
        for (int i = 0; i < date.length(); i++)
        {
            char c = date.charAt(i);
            if(Character.isDigit(c))
            {
                switch(check){
                    case 0 : month = 10 * month + Character.getNumericValue(c); break;
                    case 1: day = 10 * day + Character.getNumericValue(c); break;
                    case 2: year = year * 10 + Character.getNumericValue(c); break;
                }
            }
            else
                check++;
        }
        Intent intent = new Intent(this, JournalLoadActivity.class);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("year", year);
        startActivity(intent);
    }

}
