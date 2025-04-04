package com.example.ddma_lists;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] user = {"CPU", "RAM", "ROM"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, user);// адаптер

        listView.setAdapter(adapter);//привязка адаптера к listView

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = user[position];
            if (position == 0){
                Intent intent = new Intent(MainActivity.this, CPU.class);
                startActivity(intent);
            }
            if (position == 1){
                Intent intent2 = new Intent(MainActivity.this, RAM.class);
                startActivity(intent2);
            }
            if (position == 2){
                Intent intent3 = new Intent(MainActivity.this, ROM.class);
                startActivity(intent3);
            }
        });

        findViewById(R.id.recycler_btn).setOnClickListener(v ->{
            Intent intent4 = new Intent(MainActivity.this, RecyclerActivity.class);
            startActivity(intent4);
        });

        findViewById(R.id.scroll_btn).setOnClickListener(v ->{
            Intent intent5 = new Intent(MainActivity.this, ScrollActivity.class);
            startActivity(intent5);
        });

        findViewById(R.id.spinner_btn).setOnClickListener(v->{
            Intent intent6 = new Intent(MainActivity.this, SpinnerActivity.class);
            startActivity(intent6);
        });

    }
}