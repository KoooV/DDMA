package com.example.ddma_12_1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class JsonManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        User user = new User();
        user.setName("Alex");
        user.setAge(20);
        user.setEmail("alexkovalev2005@icloud.com");

        Gson gson = new Gson();
        String json = gson.toJson(user);

        File file = new File(getFilesDir(), "user.json");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(json.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);

            User userFromJson = gson.fromJson(isr, User.class);

            TextView textViewName = findViewById(R.id.textViewName);
            TextView textViewAge = findViewById(R.id.textViewAge);
            TextView textViewEmail = findViewById(R.id.textViewEmail);

            textViewName.setText("Name: " + userFromJson.getName());
            textViewAge.setText("Age: " + userFromJson.getAge());
            textViewEmail.setText("Email: " + userFromJson.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
