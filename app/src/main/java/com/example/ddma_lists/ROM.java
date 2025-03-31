package com.example.ddma_lists;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ROM extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState){
        String[] rom ={"256", "512", "1024"};

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rom);

        ListView listView = findViewById(R.id.list_rom);

        ArrayAdapter<String> adapterRom = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rom);
        listView.setAdapter(adapterRom);
    }

}
