package com.example.ddma_filesys;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText fileNameEditText;
    private EditText fileDataEditText;
    private TextView outputTextView;
    private Button saveButton;
    private Button checkButton;
    private Button deleteButton;
    private Button addDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        fileNameEditText = findViewById(R.id.file_name);
        fileDataEditText = findViewById(R.id.file_data);
        outputTextView = findViewById(R.id.textView);
        saveButton = findViewById(R.id.btn_save);
        checkButton = findViewById(R.id.btn_check);
        deleteButton = findViewById(R.id.btn_del);
        addDataButton = findViewById(R.id.btn_addData);

        // Set up button click listeners
        saveButton.setOnClickListener(v -> createFile());
        checkButton.setOnClickListener(v -> readFile());
        deleteButton.setOnClickListener(v -> showDeleteConfirmationDialog());
        addDataButton.setOnClickListener(v -> appendToFile());
    }

    private void createFile() {
        String fileName = fileNameEditText.getText().toString();
        String fileContent = fileDataEditText.getText().toString();

        if (fileName.isEmpty()) {
            Toast.makeText(this, "Please enter a file name", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            File file = new File(getFilesDir(), fileName);
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(fileContent.getBytes());
            }
            Toast.makeText(this, "File created successfully", Toast.LENGTH_SHORT).show();
            fileDataEditText.setText("");
        } catch (IOException e) {
            Toast.makeText(this, "Error creating file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void readFile() {
        String fileName = fileNameEditText.getText().toString();

        if (fileName.isEmpty()) {
            Toast.makeText(this, "Please enter a file name", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            File file = new File(getFilesDir(), fileName);
            if (!file.exists()) {
                Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
                return;
            }

            StringBuilder content = new StringBuilder();
            try (FileReader reader = new FileReader(file)) {
                int character;
                while ((character = reader.read()) != -1) {
                    content.append((char) character);
                }
            }
            outputTextView.setText(content.toString());
        } catch (IOException e) {
            Toast.makeText(this, "Error reading file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void appendToFile() {
        String fileName = fileNameEditText.getText().toString();
        String additionalContent = fileDataEditText.getText().toString();

        if (fileName.isEmpty()) {
            Toast.makeText(this, "Please enter a file name", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            File file = new File(getFilesDir(), fileName);
            if (!file.exists()) {
                Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
                return;
            }

            try (FileOutputStream outputStream = new FileOutputStream(file, true)) {
                outputStream.write(additionalContent.getBytes());
            }
            Toast.makeText(this, "Data appended successfully", Toast.LENGTH_SHORT).show();
            fileDataEditText.setText("");
        } catch (IOException e) {
            Toast.makeText(this, "Error appending to file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteConfirmationDialog() {
        String fileName = fileNameEditText.getText().toString();

        if (fileName.isEmpty()) {
            Toast.makeText(this, "Please enter a file name", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Delete File")
                .setMessage("Are you sure you want to delete this file?")
                .setPositiveButton("Yes", (dialog, which) -> deleteFile())
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteFile() {
        String fileName = fileNameEditText.getText().toString();
        File file = new File(getFilesDir(), fileName);

        if (!file.exists()) {
            Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            if (file.delete()) {
                Toast.makeText(this, "File deleted successfully", Toast.LENGTH_SHORT).show();
                outputTextView.setText("");
                fileDataEditText.setText("");
            } else {
                Toast.makeText(this, "Failed to delete file", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error deleting file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}