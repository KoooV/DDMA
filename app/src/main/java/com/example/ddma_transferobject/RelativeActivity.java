package com.example.ddma_transferobject;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RelativeActivity extends AppCompatActivity {
    private EditText editText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_relative);

        editText = findViewById(R.id.editText3);

        findViewById(R.id.backBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.confBtn3).setOnClickListener(v -> {
            String inputText = editText.getText().toString().trim();
            if (!inputText.isEmpty()){
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USER_GROUP", inputText);
                editor.apply();

                Intent intent = new Intent(RelativeActivity.this, LinearActivity.class);
                startActivity(intent);
            }
            else{
                editText.setError("Empty field");
            }
        });
    }
        public void onBackPressed(){
            super.onBackPressed();
    }
}

