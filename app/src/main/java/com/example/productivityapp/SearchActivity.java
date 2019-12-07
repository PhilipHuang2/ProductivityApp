package com.example.productivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    // Google Firebase Database Reference
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // Event Listener that listens to each child in the database
    private ChildEventListener childEventListener;

    // Local data structure that will store all the values from the database
    private ArrayList<Diary> DiaryList;
    private ArrayList<Diary> searchResults;

    // ArrayAdapter allows the results to be displayed in a list on the app
    private DiaryAdapter listAdapter;

    private Button SearchButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("contacts");

        // Initializes the local data structure to store the database
        DiaryList = new ArrayList<Diary>();

        // Set up an array that will have the contents that you want to display
        searchResults = new ArrayList<Diary>();

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        childEventListener = new ChildEventListener(){
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // Adds the Contact to the local data structure
                DiaryList.add(dataSnapshot.getValue(Diary.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        // Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        // Sets up the list adapter to read from the results array
        listAdapter = new DiaryAdapter( this, searchResults);
        ListView results = findViewById(R.id.searchView);
        results.setAdapter(listAdapter);


        SearchButton2 = findViewById(R.id.searchButton2);
        SearchButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }



    public void search(){
        //listAdapter.clear();    // clears any content
        boolean found = false;
        for( Diary d: DiaryList)
        {
            EditText text = findViewById(R.id.DateText);
            String search = text.getText().toString();
            if(d.getDate().equals(search)) {
                Toast.makeText(this, search + " found. Searching for: " + d.getDate() , Toast.LENGTH_LONG).show();
                // If the contact name is a match, add the result to the listAdapter for display
                listAdapter.add(d);
                found = true;
            }
        }
        EditText search = findViewById(R.id.DateText);
        if(!found) {
            Toast.makeText(this, search.getText().toString() + " not found." , Toast.LENGTH_LONG).show();
        }
        search.setText("");
    }

}
