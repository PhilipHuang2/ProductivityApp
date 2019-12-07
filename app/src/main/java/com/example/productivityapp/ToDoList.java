package com.example.productivityapp;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Map;

public class ToDoList extends AppCompatActivity {

    private com.google.firebase.database.DatabaseReference database;

    private Button addTaskButton;
    private TextInputLayout addTaskInputText;
    private ScrollView scrollView;

    private ToDoItem[] toDoItems;

    private ArrayList<ToDoItem> allToDoItems;
    // private ListAdapter<To>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        database = FirebaseDatabase.getInstance().getReference();
        addTaskButton = findViewById(R.id.addTaskButton);
        scrollView = findViewById(R.id.scrollView);

        toDoItems = new ToDoItem[5];
        allToDoItems = new ArrayList<>();

        loadToDoItems();
        populateScrollView();

        addTaskInputText = findViewById(R.id.addTaskTextInput);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddTextButtonClick();
            }
        });
    }

    // Load all Firebase data into the array of 5 to do list items (in RAM).
    private void loadToDoItems()
    {
        DatabaseReference ref = database.child("ToDoListItems");

        
    }

    // Dynamically instantiates checkboxes and sets their attributes (checked or unchecked
    // and the text), max 5, in the View.
    private void populateScrollView()
    {

    }

    // Updates the array to store the previous 5 to do list items.
    private void getPreviousFiveToDoListItems()
    {

    }

    // Updates the array to store the next 5 to do list items.
    private void getNextFiveToDoListItems()
    {

    }

    // Deletes all checked to do list items
    private void deleteCheckedToDoListItems()
    {
        // delete by key
    }

    private void onAddTextButtonClick()
    {
        // DatabaseReference ref = database.child("ToDoListItems");
        String text = this.addTaskInputText.getEditText().getText().toString();
        if (text != "") {
            insertToDoListItem(text);
            clearEditTextField();
        }
    }

    private void insertToDoListItem(String text)
    {
        String key = database.child("ToDoListItems").push().getKey();
        ToDoItem toDoItem = new ToDoItem(text);
        database.child("ToDoListItems").child(key).setValue(toDoItem);
    }

    private void clearEditTextField()
    {
        addTaskInputText.getEditText().getText().clear();
    }
}
