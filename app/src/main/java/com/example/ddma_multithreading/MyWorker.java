package com.example.ddma_multithreading;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker{
    private static final String TAG = "CounterWorker";
    private static final String KEY_COUNTER = "counter";
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        int counter = getInputData().getInt(KEY_COUNTER, 0);
        counter++;
        Log.d(TAG, "текущее значение" + " " + counter);

        Data outData = new Data.Builder().putInt(KEY_COUNTER,counter).build();
        return Result.success(outData);


    }
}
