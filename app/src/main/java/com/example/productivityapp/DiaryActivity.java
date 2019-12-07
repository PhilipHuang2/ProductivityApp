package com.example.productivityapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;

import java.util.Date;


public class DiaryActivity extends AppCompatActivity {


    private DatabaseReference myRef;
    private FirebaseDatabase database;

    private Button newEntryButton, SearchButton, DeleteButton;

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    String dateString = format.format( new Date()   );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        newEntryButton = findViewById(R.id.newEntryButton);
        newEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaryEntry();
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openDiaryEntry(){

        String m = dateString.substring(0,2);
        int month = Integer.parseInt(m);
        String d = dateString.substring(3,5);
        int day = Integer.parseInt(d);
        String y = dateString.substring(6,10);
        int year = Integer.parseInt(y);


        //newEntryButton.setText(dateString);
        Intent intent = new Intent(this, JournalEntryActivity.class);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("year", year);
        startActivity(intent);

    }

}
