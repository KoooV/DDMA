package com.example.ddma_multithreading;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnthread = findViewById(R.id.button1);
        btnthread.setOnClickListener(v -> startThread());

    }
    protected void onResume(){
        boolean mainThreadRun = true;
        while(mainThreadRun){
            Toast.makeText(this, "Main", Toast.LENGTH_SHORT).show();
        }

    }

    private void startThread() {
        boolean threadRun = true;
        new Thread(() -> {
            while(threadRun){
                try{
                Log.d("RRR", "MainThread");
                Toast.makeText(this, "Second", Toast.LENGTH_SHORT );
                Thread.sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();


    }
}