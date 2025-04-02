package com.example.ddma_lists;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class CPU extends AppCompatActivity {

      String[] type = {"Intel", "Amd", "M"};
      ArrayList<String> data = new ArrayList<>(Arrays.asList(type));
      protected void onCreate(Bundle savedInstanceState){

         super.onCreate(savedInstanceState);
         EdgeToEdge.enable(this);
         setContentView(R.layout.activity_cpu);

         ListView listView = findViewById(R.id.list_age);
         ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, data);// адаптер
         listView.setAdapter(adapter);

         Button addBtn = findViewById(R.id.add_btn);

         addBtn.setOnClickListener(v -> {
             data.add("Эльбрус ");// добавление списка
             adapter.notifyDataSetChanged();// обновление списка
         });

         listView.setOnItemClickListener((parent, view, position, id) ->{
             data.remove(position);// удаление по клику
             adapter.notifyDataSetChanged();
             /*return true;*/ // для удаления долгим нажатием
         });

     }

}
