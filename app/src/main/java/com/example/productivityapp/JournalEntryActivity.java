package com.example.productivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JournalEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);
        Button submitButton;
        final EditText paragraph;

        Intent intent = getIntent();
        TextView title;

        String date = intent.getStringExtra("currentDate");
        title = findViewById(R.id.title);
        title.setText(date);
        paragraph = findViewById(R.id.EditTextBox1);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paragraph.setText("");
                finish();
            }
        });
    }
}
