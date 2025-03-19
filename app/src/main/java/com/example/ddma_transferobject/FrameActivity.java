package com.example.ddma_transferobject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FrameActivity extends AppCompatActivity {
    private ImageButton backBtn;
    private EditText editText;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frame);

        backBtn = findViewById(R.id.backBtn);
        editText = findViewById(R.id.editText2);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

        findViewById(R.id.confBtn2).setOnClickListener(v ->{
            String inputText = editText.getText().toString().trim();

            if(!inputText.isEmpty()){
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USER_AGE", inputText);
                editor.apply();

                Intent intent = new Intent(FrameActivity.this, RelativeActivity.class );
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
