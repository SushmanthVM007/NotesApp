package com.sushmanth.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView notesRecyclerView;
    private FloatingActionButton addNoteButton;
    private Spinner categoryFilterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        addNoteButton = findViewById(R.id.addNoteButton);
        categoryFilterSpinner = findViewById(R.id.categoryFilterSpinner);

        addNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, NoteCreationActivity.class);
            startActivity(intent);
        });
        // Add RecyclerView adapter and SQLite database code here to display and filter notes
    }
}
