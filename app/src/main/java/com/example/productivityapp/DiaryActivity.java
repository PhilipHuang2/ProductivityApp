

/*
    Written by Carlos Emilio Yan Ho
*/


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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


public class DiaryActivity extends AppCompatActivity {

    // Google Firebase Database Reference
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // Event Listener that listens to each child in the database
    private ChildEventListener childEventListener;

    // ArrayAdapter allows the results to be displayed in a list on the app
    private DiaryAdapter listAdapter;

    //Button references
    private Button newEntryButton, SearchButton, DeleteButton;

    //Get the Current date
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    String dateString = format.format( new Date()   );

    Diary deleteTarget ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializes the references to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Diary");



        // Set up an array that will have the contents that you want to display
        ArrayList<Diary> diaryList = new ArrayList<Diary>();

        // Sets up the event listener that will specify what happens when access of a node
        // occurs in the database
        childEventListener = new ChildEventListener(){
            @Override
            // Method is run when any new node is added to the database, and once
            // for every existing node when the activity is loaded
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                listAdapter.add( dataSnapshot.getValue(Diary.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                listAdapter.notifyDataSetChanged();
                //listAdapter.add( dataSnapshot.getValue(Diary.class));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };

        // Need to add the event listener to the database
        myRef.addChildEventListener(childEventListener);

        // Sets up the list adapter to read from the results array
        listAdapter = new DiaryAdapter(this, diaryList );
        ListView results = findViewById(R.id.EntryList);
        results.setAdapter(listAdapter);



        // Defines what happens when an item of the listView is clicked
        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView and place it into the deleteTarget variable
                Diary selectedItem = (Diary) parent.getItemAtPosition(position);
                deleteTarget = selectedItem;


            }
        });

        //Activity of the newEntryButton
        newEntryButton = findViewById(R.id.newEntryButton);
        newEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaryEntry();
            }
        });

        //Activity of the searchEntryButton
        SearchButton = findViewById(R.id.searchEntryButton);
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });

        //Activity for DeleteButton
        DeleteButton = findViewById(R.id.deleteEntry);
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(deleteTarget.getID()).removeValue();

                // Removes the Contact from the local data structure
                listAdapter.remove(deleteTarget);
            }
        });
    }

    public void openDiaryEntry(){

        String m = dateString.substring(0,2);
        int month = Integer.parseInt(m);
        String d = dateString.substring(3,5);
        int day = Integer.parseInt(d);
        String y = dateString.substring(6,10);
        int year = Integer.parseInt(y);


        //newEntryButton.setText(dateString);
        Intent intent = new Intent(this, JournalEntryActivity.class);
        intent.putExtra("month", month);
        intent.putExtra("day", day);
        intent.putExtra("year", year);
        startActivity(intent);

    }

    public void openSearchActivity(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

}
