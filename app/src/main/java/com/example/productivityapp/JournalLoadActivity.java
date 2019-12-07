package com.example.productivityapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JournalLoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_load);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Diary");
        ChildEventListener ChildEventListener;

        TextView loadJournalTitle;

        Intent intent = getIntent();
        final int day = intent.getIntExtra("day",0);
        final int month = intent.getIntExtra("month",0);
        final int year = intent.getIntExtra("year",0);

        loadJournalTitle = findViewById(R.id.loadJournalTitle);
        String date = "The Diaries from " + month + "/" + day +  "/" + year;
        loadJournalTitle.setText(date);
    }

}
