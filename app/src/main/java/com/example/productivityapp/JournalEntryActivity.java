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
        TextView title;

        final String date = intent.getStringExtra("currentDate");
        title = findViewById(R.id.title);
        title.setText(date);
        paragraph = findViewById(R.id.EditTextBox1);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    check++;
                }
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
