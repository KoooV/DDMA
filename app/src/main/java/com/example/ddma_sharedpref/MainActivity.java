package com.example.ddma_sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText Name;
    Button saveBtn;
    Button checkBtn;
    Button delBtn;
    private SharedPreferences sharedPreferences;
    private static final String TAG = "SharedPref";
    private static final String PREF_NAME = "MyPrefs"; // Имя файла SharedPreferences
    private static final String KEY_USER = "username"; // Ключ для хранения имени пользователя
    private static final String KEY_LOGIN_COUNT = "login_count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.name);
        saveBtn = findViewById(R.id.saveBtn);
        checkBtn = findViewById(R.id.checkBtn);
        delBtn = findViewById(R.id.delBtn);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        saveBtn.setOnClickListener(v -> {saveUsername();});
        checkBtn.setOnClickListener(v ->{checkUserName();});
        delBtn.setOnClickListener(v -> delUserName());
    }

    private void saveUsername() {
        String username = Name.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER, username);
        editor.apply();

        Log.d(TAG, "Имя пользователя сохранено: " + username);
    }

    private void checkUserName(){
        String UserName = sharedPreferences.getString(KEY_USER, "Имя не установлено");
        Toast.makeText(this, "Текущее имя " + UserName, Toast.LENGTH_SHORT).show();

        Log.d(TAG, "Текущее имя: " + UserName);
    }

    private void delUserName(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USER);
        editor.apply();
        Toast.makeText(this, "Имя удалено", Toast.LENGTH_SHORT).show();
    }
}