package com.example.ddma_transferobject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LinearActivity extends AppCompatActivity {
    private TextView userName;
    private TextView userAge;
    private TextView userGroup;

    protected void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_linear);

        userName = findViewById(R.id.userName);
        userAge = findViewById(R.id.userAge);
        userGroup = findViewById(R.id.userGroup);

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedGroup = prefs.getString("USER_GROUP", "Group not select");
        String savedName = prefs.getString("USER_NAME", "Name not select");
        String savedAge = prefs.getString("USER_AGE", "Age not select");

        userName.setText("Name: "+ savedName);
        userAge.setText("Age: " + savedAge);
        userGroup.setText("Group: " + savedGroup);



    }

}

