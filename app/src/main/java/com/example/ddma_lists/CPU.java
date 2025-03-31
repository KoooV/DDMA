package com.example.ddma_lists;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class CPU extends AppCompatActivity {

      String[] age = {"Intel", "Amd", "M"};
      protected void onCreate(Bundle savedInstanceState){

         super.onCreate(savedInstanceState);
         EdgeToEdge.enable(this);
         setContentView(R.layout.activity_cpu);

         ListView listView = findViewById(R.id.list_age);
         ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, age);// адаптер
         listView.setAdapter(adapter);

     }

}
