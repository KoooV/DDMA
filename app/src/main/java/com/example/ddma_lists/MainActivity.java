package com.example.ddma_lists;

import android.content.Intent;
import android.os.Bundle;

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
    private RecyclerView categoriesRecyclerView;
    private List<String> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Заполнение списка категорий
        categories.add("Яблоки");
        categories.add("Груши");
        categories.add("Бананы");

        // Настройка RecyclerView
        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        CategoryAdapter adapter = new CategoryAdapter(categories, this::onCategoryClick);
        categoriesRecyclerView.setAdapter(adapter);
    }

    // Обработка выбора категории
    private void onCategoryClick(String category) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}