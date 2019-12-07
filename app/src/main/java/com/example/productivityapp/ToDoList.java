package com.example.productivityapp;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import java.util.Map;

public class ToDoList extends AppCompatActivity {

    private com.google.firebase.database.DatabaseReference database;

    private Button addTaskButton;
    private TextInputLayout addTaskInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        database = FirebaseDatabase.getInstance().getReference();
        addTaskButton = findViewById(R.id.addTaskButton);

        // loadDataIntoCurrentCheckboxes();

        addTaskInputText = findViewById(R.id.addTaskTextInput);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddTextButtonClick();
            }
        });
    }

    private void loadDataIntoCurrentCheckboxes()
    {
        // load any previous stored data into the current 5 checkboxes

        String checkBoxesText[] = new String[5];
        // database.child("ToDoList").child("");
    }

    private void onAddTextButtonClick()
    {
        // DatabaseReference ref = database.child("ToDoListItems");
        String text = this.addTaskInputText.getEditText().getText().toString();
        if (text != "") {
            // insert to do list item
            String key = database.child("ToDoListItems").push().getKey();
            ToDoItem toDoItem = new ToDoItem(text);
            database.child("ToDoListItems").child(key).setValue(toDoItem);

            // clear the edit text field
            addTaskInputText.getEditText().getText().clear();
        }
    }
}
