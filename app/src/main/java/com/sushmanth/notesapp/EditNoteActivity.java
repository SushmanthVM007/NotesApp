package com.sushmanth.notesapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextContent;
    private Spinner spinnerCategory;
    private Button buttonUpdate;
    private DatabaseHelper databaseHelper;
    private int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        databaseHelper = new DatabaseHelper(this);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        // Get the note ID from the intent
        Intent intent = getIntent();
        noteId = intent.getIntExtra("NOTE_ID", -1);

        // Load note details
        loadNoteDetails();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();
                String category = spinnerCategory.getSelectedItem().toString();

                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(EditNoteActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdated = databaseHelper.updateNote(noteId, title, content, category);
                    if (isUpdated) {
                        Toast.makeText(EditNoteActivity.this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditNoteActivity.this, "Failed to update note", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void loadNoteDetails() {
        Cursor cursor = databaseHelper.getNoteById(noteId);
        if (cursor != null && cursor.moveToFirst()) {
            editTextTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
            editTextContent.setText(cursor.getString(cursor.getColumnIndex("content")));
            // Set spinner selection based on category if needed
            cursor.close();
        }
    }
}
