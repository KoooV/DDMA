package com.example.ddma_multithreading;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker.*;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";
    private static final String KEY_COUNTER = "counter";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Data inputData = new Data.Builder().putInt(KEY_COUNTER, 0).build();//начальные данные

        OneTimeWorkRequest work1 = new OneTimeWorkRequest.Builder(MyWorker.class)// первый поток
                .setInputData(inputData)
                .build();

        OneTimeWorkRequest work2 = new OneTimeWorkRequest.Builder(MyWorker.class)// второй поток
                .setInputData(inputData)
                .build();

        OneTimeWorkRequest work3 = new OneTimeWorkRequest.Builder(MyWorker.class)// третий поток
                .setInputData(inputData)
                .build();

        // getInstance - метод для получения единого жкземпляра WorkManager во всем приложении
        findViewById(R.id.button1).setOnClickListener(v ->{WorkManager.getInstance(this)
                .beginWith(work1)
                .then(work2)
                .then(work3)
                .enqueue();
        });

        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(work3.getId())
                .observe(this, workInfo ->{
                    if(workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED){
                        int finalCounter = workInfo.getOutputData().getInt(KEY_COUNTER,0);
                        Log.d(TAG, "Final num" + " " + finalCounter);
                    }
                } );
    }
}



