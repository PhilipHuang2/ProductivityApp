package com.example.productivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class JournalEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);

        Intent intent = getIntent();
        TextView title;

        String date = intent.getStringExtra("currentDate");
        title = findViewById(R.id.title);
        title.setText(date);
    }
}
