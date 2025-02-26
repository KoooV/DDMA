package com.example.ddma_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {


    public static final String KEY_NAME = "name";
    public static final String KEY_GROUP = "group_number";
    public static final String KEY_AGE = "age";
    public static final String KEY_SCORE = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "onCreate() called");

        Button button = findViewById(R.id.b_send);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);

            // Передача данных с правильными ключами
            intent.putExtra(KEY_NAME, "Kovalev A.E.");
            intent.putExtra(KEY_GROUP, "IKBO-76-23");
            intent.putExtra(KEY_AGE, 19);
            intent.putExtra(KEY_SCORE, 10);

            startActivity(intent); // Добавлен запуск активити
            });

            Button pButton = findViewById(R.id.pButton);
            pButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Toast.makeText(MainActivity.this, "Programm click", Toast.LENGTH_SHORT).show();
                }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("MainActivity", "onStart() called");
    }

    @Override
    protected void onResume(){

        super.onResume();
        Log.d("MainActivity", "onResume() called" );
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("MainActivity", "onPause() called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d("MainActivity", "onStop() called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("MainActivity", "onDestroy() called");
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}