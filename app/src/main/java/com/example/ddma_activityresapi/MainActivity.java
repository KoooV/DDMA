package com.example.ddma_activityresapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etFirstName;
    private EditText etLastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();

            if (!firstName.isEmpty() && !lastName.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("FIRST_NAME", firstName);
                intent.putExtra("LAST_NAME", lastName);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
        });

    }
}