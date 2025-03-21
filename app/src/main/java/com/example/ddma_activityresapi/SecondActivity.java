package com.example.ddma_activityresapi;
import static android.content.Intent.getIntent;
import static androidx.core.app.ActivityCompat.startActivityForResult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class SecondActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvName = findViewById(R.id.tvFullName);
        EditText etSubject = findViewById(R.id.etSubject);
        Button btnEnter = findViewById(R.id.btnEnter);

        String firstName = getIntent().getStringExtra("FIRST_NAME");
        String lastName = getIntent().getStringExtra("LAST_NAME");
        tvName.setText(firstName + " " + lastName);

        btnEnter.setOnClickListener(v -> {
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String time = data.getStringExtra("TIME");
            Toast.makeText(this, "Время занятия: " + time, Toast.LENGTH_LONG).show();
        }
    }
}
