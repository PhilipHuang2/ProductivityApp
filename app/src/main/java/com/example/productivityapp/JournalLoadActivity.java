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
import com.google.firebase.database.ValueEventListener;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class JournalLoadActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private DiaryAdapter listAdapter;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_load);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Diary");
        ArrayList<Diary> diaryList = new ArrayList<>();

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                listAdapter.add(dataSnapshot.getValue(Diary.class));
                System.out.println("\n");
                System.out.println("Month: " + dataSnapshot.getValue(Diary.class).getMonth());
                System.out.println("Day: " + dataSnapshot.getValue(Diary.class).getDay());
                System.out.println("Year: " + dataSnapshot.getValue(Diary.class).getYear());
                System.out.println("Diary: " + dataSnapshot.getValue(Diary.class).getDiary());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener(childEventListener);

        listAdapter = new DiaryAdapter(this, diaryList);
        ListView results = findViewById(R.id.listViewResults);
        results.setAdapter(listAdapter);


        Intent intent = getIntent();
        final int day = intent.getIntExtra("day",0);
        final int month = intent.getIntExtra("month",0);
        final int year = intent.getIntExtra("year",0);

        TextView loadJournalTitle;
        loadJournalTitle = findViewById(R.id.loadJournalTitle);
        String date = "The Diaries from " + month + "/" + day +  "/" + year;
        loadJournalTitle.setText(date);


    }

}
