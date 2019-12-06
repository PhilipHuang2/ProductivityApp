package com.example.productivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JournalEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);
        Button submitButton;
        final EditText paragraph;

        Intent intent = getIntent();
        final int day = intent.getIntExtra("day",0);
        final int month = intent.getIntExtra("month",0);
        final int year = intent.getIntExtra("year",0);

        TextView title;
        title = findViewById(R.id.title);
        String date = (month) + "/" + day +  "/" + year;
        title.setText(date);

        paragraph = findViewById(R.id.EditTextBox1);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dummy = paragraph.getText().toString();
                Diary newEntry = new Diary(month,day,year,dummy);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Diary");
                String key = myRef.push().getKey();
                myRef.child(key).setValue(newEntry);
                finish();
            }
        });
    }
}
