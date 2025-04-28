package com.example.ddma_multithreading;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ParallelCounterWorker extends Worker {
    private static final String TAG = "ParallelCounter";
    private static final String KEY_COUNTER = "parallel_counter";
    private static final int MAX_COUNT = 3;

    public ParallelCounterWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            // Получаем текущее значение счетчика
            int counter = getInputData().getInt(KEY_COUNTER, 0);
            
            // Проверяем, не достигли ли мы максимума
            if (counter >= MAX_COUNT) {
                Log.d(TAG, "Counter reached maximum value: " + MAX_COUNT);
                return Result.success();
            }
            
            // Увеличиваем счетчик
            counter++;
            
            // Логируем текущее значение
            Log.d(TAG, "Parallel counter value: " + counter);
            
            // Создаем выходные данные с обновленным значением счетчика
            androidx.work.Data outputData = new androidx.work.Data.Builder()
                    .putInt(KEY_COUNTER, counter)
                    .build();
            
            return Result.success(outputData);
        } catch (Exception e) {
            Log.e(TAG, "Error in parallel worker: " + e.getMessage());
            return Result.failure();
        }
    }
} 