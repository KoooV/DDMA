package com.example.ddma_sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private Button saveBtn;
    private Button checkBtn;
    private Button delBtn;
    private SharedPreferences sharedPreferences;
    private static final String TAG = "SharedPref";
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_USER = "username";
    private static final String KEY_LOGIN_COUNT = "login_count";

    // Поля для работы с базой данных
    private EditText DbName;
    private EditText Email;
    private EditText Phone;
    private EditText Age;
    private EditText Address;
    private EditText searchName;
    private EditText idInput;
    private Button saveToDbBtn;
    private Button searchBtn;
    private Button updateBtn;
    private Button deleteBtn;
    private TextView userInfoText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Инициализация существующих элементов
        Name = findViewById(R.id.name);
        saveBtn = findViewById(R.id.saveBtn);
        checkBtn = findViewById(R.id.checkBtn);
        delBtn = findViewById(R.id.delBtn);

        // Инициализация новых элементов для базы данных
        DbName = findViewById(R.id.dbName);
        Email = findViewById(R.id.email);
        Phone = findViewById(R.id.phone);
        Age = findViewById(R.id.age);
        Address = findViewById(R.id.address);
        searchName = findViewById(R.id.searchName);
        idInput = findViewById(R.id.idInput);
        saveToDbBtn = findViewById(R.id.saveToDbBtn);
        searchBtn = findViewById(R.id.searchBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        userInfoText = findViewById(R.id.userInfoText);
        dbHelper = new DatabaseHelper(this);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);


        saveBtn.setOnClickListener(v -> {saveUsername();});
        checkBtn.setOnClickListener(v ->{checkUserName();});
        delBtn.setOnClickListener(v -> delUserName());


        saveToDbBtn.setOnClickListener(v -> saveUser());
        searchBtn.setOnClickListener(v -> searchUser());
        updateBtn.setOnClickListener(v -> updateUser());
        deleteBtn.setOnClickListener(v -> deleteUser());
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

    
    private void saveUser() {
        String name = DbName.getText().toString();
        String email = Email.getText().toString();
        String phone = Phone.getText().toString();
        String ageStr = Age.getText().toString();
        String address = Address.getText().toString();

        // Проверка на пустые поля
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || ageStr.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            long id = dbHelper.insertUser(name, email, phone, age, address);
            if (id != -1) {
                Toast.makeText(this, "Пользователь успешно добавлен в БД", Toast.LENGTH_SHORT).show();
                clearInputs();
                // Показываем добавленного пользователя
                searchUser();
            } else {
                Toast.makeText(this, "Ошибка при добавлении пользователя в БД", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Введите корректный возраст", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchUser() {
        String name = searchName.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "Введите имя для поиска", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor cursor = dbHelper.searchUserByName(name);
        StringBuilder result = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                result.append("ID: ").append(cursor.getLong(0))
                      .append("\nИмя: ").append(cursor.getString(1))
                      .append("\nEmail: ").append(cursor.getString(2))
                      .append("\nТелефон: ").append(cursor.getString(3))
                      .append("\nВозраст: ").append(cursor.getInt(4))
                      .append("\nАдрес: ").append(cursor.getString(5))
                      .append("\n\n");
            } while (cursor.moveToNext());
        } else {
            result.append("Пользователь не найден");
        }
        cursor.close();
        
        // Обновляем TextView с информацией о пользователе
        userInfoText.setText(result.toString());
    }

    private void updateUser() {
        String name = DbName.getText().toString();
        String email = Email.getText().toString();
        String phone = Phone.getText().toString();
        String ageStr = Age.getText().toString();
        String address = Address.getText().toString();

        // Проверка на пустые поля
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || ageStr.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            // Предполагаем, что ID пользователя вводится в поле Name
            long id = Long.parseLong(Name.getText().toString());
            int result = dbHelper.updateUser(id, name, email, phone, age, address);
            if (result > 0) {
                Toast.makeText(this, "Пользователь успешно обновлен в БД", Toast.LENGTH_SHORT).show();
                clearInputs();
                // Обновляем информацию в TextView
                searchUser();
            } else {
                Toast.makeText(this, "Ошибка при обновлении пользователя в БД", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Введите корректный возраст", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteUser() {
        String idStr = idInput.getText().toString().trim();
        if (idStr.isEmpty()) {
            Toast.makeText(this, "Введите ID для удаления", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            long id = Long.parseLong(idStr);
            int result = dbHelper.deleteUser(id);
            if (result > 0) {
                Toast.makeText(this, "Пользователь успешно удален из БД", Toast.LENGTH_SHORT).show();
                idInput.setText(""); // Очищаем поле ввода ID
                userInfoText.setText("Информация о пользователе будет отображаться здесь");
            } else {
                Toast.makeText(this, "Пользователь с таким ID не найден", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Введите корректный ID пользователя", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        DbName.setText("");
        Email.setText("");
        Phone.setText("");
        Age.setText("");
        Address.setText("");
    }

    private void searchInDatabase() {
        String searchNameStr = searchName.getText().toString().trim();
        if (searchNameStr.isEmpty()) {
            Toast.makeText(this, "Введите имя для поиска", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {"name", "age"};
        String selection = "name LIKE ?";
        String[] selectionArgs = {"%" + searchNameStr + "%"};
        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);

        StringBuilder result = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
                result.append("Имя: ").append(name).append(", Возраст: ").append(age).append("\n");
            } while (cursor.moveToNext());
        } else {
            result.append("Запись не найдена");
        }
        cursor.close();
        userInfoText.setText(result.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}