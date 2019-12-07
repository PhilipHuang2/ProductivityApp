// Jonathan Huang
// Implemented all functions, and all the functions were almost working fine,
// but something happened and the app keeps crashing.

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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ToDoList extends AppCompatActivity {

    private com.google.firebase.database.DatabaseReference database;

    private Button addTaskButton;
    private Button clearCheckedEventsButton;
    private Button prevFiveButton;
    private Button nextFiveButton;

    private TextInputLayout addTaskInputText;
    private LinearLayout scrollViewLinearLayout;

    private Map<String, ToDoItem> toDoItems;
    private Map<String, ToDoItem> allToDoItems;

    private int toDoItemWindowStart;
    private int toDoItemWindowEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        database = FirebaseDatabase.getInstance().getReference();

        addTaskButton = findViewById(R.id.addTaskButton);
        clearCheckedEventsButton = findViewById(R.id.clearCheckedEvents);
        prevFiveButton = findViewById(R.id.prevButton);
        nextFiveButton = findViewById(R.id.nextButton);

        scrollViewLinearLayout = findViewById(R.id.scrollViewLinearLayout);
        addTaskInputText = findViewById(R.id.addTaskTextInput);

        toDoItems = new HashMap<>(5);
        allToDoItems = new HashMap<>();

        loadToDoItems();
        loadToDoItemsIntoTempArrayList();
        populateScrollView();

        toDoItemWindowStart = 0;
        toDoItemWindowEnd = 4;

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddTextButtonClick();
            }
        });

        clearCheckedEventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCheckedToDoListItems();
            }
        });

        prevFiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPreviousFiveToDoListItems();
            }
        });

        nextFiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNextFiveToDoListItems();
            }
        });
    }

    // Load all Firebase data into the array of 5 to do list items (in RAM).
    private void loadToDoItems()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference("ToDoListItems");
        //DatabaseReference ref = db.getReference("");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                ToDoItem toDoItem = dataSnapshot.getValue(ToDoItem.class);
                allToDoItems.put(dataSnapshot.getKey(), toDoItem);
//                if (toDoItems.size() < 5) {
//                    toDoItems.put(dataSnapshot.getKey(), toDoItem);
//                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    public void loadToDoItemsIntoTempArrayList()
    {
        for (String key : allToDoItems.keySet()) {
            if (toDoItems.size() < 5) {
                toDoItems.put(key, allToDoItems.get(key));
            }
            else {
                break;
            }
        }
    }

    // Dynamically instantiates checkboxes and sets their attributes (checked or unchecked
    // and the text), max 5, in the ScrollView.
    private void populateScrollView()
    {
        for (ToDoItem tdi : toDoItems.values()) {
            CheckBox checkBox = new CheckBox(getApplicationContext());
            checkBox.setText(tdi.getItem());
            checkBox.setChecked(tdi.getComplete());
            scrollViewLinearLayout.addView(checkBox);
        }
    }

    // Updates the array to store the previous 5 to do list items.
    private void getPreviousFiveToDoListItems()
    {
        if (toDoItemWindowStart != 0) {
            toDoItemWindowStart -= 5;
            toDoItemWindowEnd -= 5;
        }
    }

    // Updates the array to store the next 5 to do list items.
    private void getNextFiveToDoListItems()
    {
        if (toDoItemWindowEnd + 5 <= allToDoItems.size() - 1) {
            toDoItemWindowStart += 5;
            toDoItemWindowEnd += 5;
        }
    }

    // Deletes all checked to do list items
    private void deleteCheckedToDoListItems()
    {
        for (String key : toDoItems.keySet()) {
             if (toDoItems.get(key).getComplete()) {
                database.child("ToDoListItems").child(key).removeValue();
            }
        }
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
