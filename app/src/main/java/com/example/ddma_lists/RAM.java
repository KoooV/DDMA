package com.example.ddma_lists;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RAM extends AppCompatActivity {

    String[] ram = {"16", "32", "64"};
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ram);

        ListView listView = findViewById(R.id.list_ram);

        ArrayAdapter<String> adapterRam = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ram);

        listView.setAdapter(adapterRam);
    }

}
