package com.example.ddma_12_1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button buttonJson = findViewById(R.id.buttonJson);
        buttonJson.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, JsonManagerActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    public void onClickAddDetails(View view) {
        ContentValues values = new ContentValues();

        values.put(MyContentProvider.name, ((EditText) findViewById(R.id.textName)).getText().toString());

        getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(), "New record inserted", Toast.LENGTH_LONG).show();
    }
}