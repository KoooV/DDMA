package com.example.ddma_lists;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ddma_lists.R;

public class SpinnerActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        // Получаем ссылку на Spinner
        Spinner spinner = findViewById(R.id.spinner);

        // Создаем адаптер с массивом данных и стандартным layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );

        // Указываем layout для выпадающего списка
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        // Устанавливаем адаптер на Spinner
        spinner.setAdapter(adapter);

        // Регистрируем обработчик выбора элементов
        spinner.setOnItemSelectedListener(this);
    }

    // Обработчик выбора элемента
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, "Выбрано: " + selectedItem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Действие при отсутствии выбора
    }
}