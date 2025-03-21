package com.example.ddma_activityresapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    private EditText etDay, etTime, etComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etDay = findViewById(R.id.etDay);
        etTime = findViewById(R.id.etTime);
        etComment = findViewById(R.id.etComment);
        Button btnOk = findViewById(R.id.btnOk);

        btnOk.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("TIME", etTime.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}