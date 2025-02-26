package com.example.ddma_activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.KEY_NAME);
        String group = intent.getStringExtra(MainActivity.KEY_GROUP);
        int age = intent.getIntExtra(MainActivity.KEY_AGE, 0);
        int score = intent.getIntExtra(MainActivity.KEY_SCORE, 0);


        TextView Name = findViewById(R.id.tv1);
        TextView Group = findViewById(R.id.tv2);
        TextView Age = findViewById(R.id.tv3);
        TextView Score = findViewById(R.id.tv4);

        Name.setText("Имя: " + name);
        Group.setText("Группа: " + group);
        Age.setText("Возраст: " + age);
        Score.setText("Оценка: " + score);
    }
}
