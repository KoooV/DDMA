package com.example.ddma_lists;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView itemsRecyclerView;
    private ItemAdapter adapter;
    private List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Получение выбранной категории
        String category = getIntent().getStringExtra("category");
        setTitle(category);

        // Заполнение списка (пример для "Яблоки")
        if ("Яблоки".equals(category)) {
            items.add("Голден");
            items.add("Гренни Смит");
            items.add("Фуджи");
        } else {
            items.add("Элемент 1");
            items.add("Элемент 2");
        }

        // Настройка RecyclerView
        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(items, this::onDeleteClick);
        itemsRecyclerView.setAdapter(adapter);

        // Кнопка "Добавить"
        findViewById(R.id.addButton).setOnClickListener(v -> showAddDialog());
    }

    // Диалог для добавления элемента
    private void showAddDialog() {
        EditText input = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("Добавить сорт")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    String newItem = input.getText().toString();
                    items.add(newItem);
                    adapter.notifyItemInserted(items.size() - 1);
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    // Удаление элемента
    private void onDeleteClick(int position) {
        items.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
