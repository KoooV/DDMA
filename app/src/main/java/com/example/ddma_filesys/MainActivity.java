package com.example.ddma_filesys;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText fileName;
    private EditText fileData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btn_save = findViewById(R.id.btn_save);
        Button btn_del = findViewById(R.id.btn_del);
        Button btn_addData = findViewById(R.id.btn_addData);
        Button btn_check = findViewById(R.id.btn_check);

        btn_save.setOnClickListener(v -> createFile());
        btn_del.setOnClickListener(v -> delFile());
        btn_check.setOnClickListener(v -> checkFile());
        btn_addData.setOnClickListener(v -> addToFile());
    }

    private void createFile(){
        String Name = fileName.getText().toString().trim();//получение названия файла из editText
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

        if(!storageDir.exists()){// проверка существования директории
            storageDir.mkdirs();//отличается от mkdir(), используется при неуверенности наличия родительских директорий(большая вложенность)
        }

        File file = new File(storageDir, Name);
        try{
            if(!file.exists()){
                boolean createdFile = file.createNewFile();
            }


    }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void addToFile(){}

    private void checkFile(){}

    private void delFile(){

    }

}