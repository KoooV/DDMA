package com.example.ddma_sharedpref;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserDB";
    private static final int DATABASE_VERSION = 1;

    // Название таблицы и колонок
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_ADDRESS = "address";

    // SQL запрос для создания таблицы
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PHONE + " TEXT,"
            + COLUMN_AGE + " INTEGER,"
            + COLUMN_ADDRESS + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Метод для добавления нового пользователя
    public long insertUser(String name, String email, String phone, int age, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_ADDRESS, address);
        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }

    // Метод для получения всех пользователей
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS, null, null, null, null, null, null);
    }

    // Метод для поиска пользователя по ID
    public Cursor getUserById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS, null, COLUMN_ID + "=?", 
                new String[]{String.valueOf(id)}, null, null, null);
    }

    // Метод для поиска пользователя по имени
    public Cursor searchUserByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_USERS, null, COLUMN_NAME + " LIKE ?", 
                new String[]{"%" + name + "%"}, null, null, null);
    }

    // Метод для обновления данных пользователя
    public int updateUser(long id, String name, String email, String phone, int age, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_ADDRESS, address);
        return db.update(TABLE_USERS, values, COLUMN_ID + "=?", 
                new String[]{String.valueOf(id)});
    }

    // Метод для удаления пользователя
    public int deleteUser(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USERS, COLUMN_ID + "=?", 
                new String[]{String.valueOf(id)});
    }
} 