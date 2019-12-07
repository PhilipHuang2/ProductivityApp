package com.example.productivityapp;
//Author Philip Huang
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button, button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.toDoListButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openToDoList();
            }
        });

        button1 = findViewById(R.id.DiaryButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiary();
            }
        });

        button2 = findViewById(R.id.CalenderButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalender();
            }
        });
    }
    public void openToDoList()
    {
        Intent intent = new Intent(this, ToDoList.class);
        startActivity(intent);
    }

    public void openDiary()
    {
        Intent intent = new Intent(this, DiaryActivity.class);
        startActivity(intent);
    }

    public void openCalender()
    {
        Intent intent = new Intent(this, CalenderActivity.class);
        startActivity(intent);
    }

}
