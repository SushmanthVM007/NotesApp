package com.sushmanth.notesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NoteCreationActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextContent;
    private Spinner spinnerCategory;
    private Button buttonSave;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_creation);

        databaseHelper = new DatabaseHelper(this);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();
                String category = spinnerCategory.getSelectedItem().toString();

                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(NoteCreationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = databaseHelper.insertNote(title, content, category);
                    if (isInserted) {
                        Toast.makeText(NoteCreationActivity.this, "Note saved successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(NoteCreationActivity.this, "Failed to save note", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
