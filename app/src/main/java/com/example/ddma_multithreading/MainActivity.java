package com.example.ddma_multithreading;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main";
    private static final String KEY_COUNTER = "counter";
    private static final String KEY_PARALLEL_COUNTER = "parallel_counter";
    private static final String KEY_IMAGE_URL = "image_url";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        // Настройка последовательного счетчика
        Data inputData = new Data.Builder().putInt(KEY_COUNTER, 0).build();

        OneTimeWorkRequest work1 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(inputData)
                .build();

        OneTimeWorkRequest work2 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(inputData)
                .build();

        OneTimeWorkRequest work3 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(inputData)
                .build();

        // Настройка параллельного счетчика
        Data parallelInputData = new Data.Builder().putInt(KEY_PARALLEL_COUNTER, 0).build();

        OneTimeWorkRequest parallelWork1 = new OneTimeWorkRequest.Builder(ParallelCounterWorker.class)
                .setInputData(parallelInputData)
                .build();

        OneTimeWorkRequest parallelWork2 = new OneTimeWorkRequest.Builder(ParallelCounterWorker.class)
                .setInputData(parallelInputData)
                .build();

        OneTimeWorkRequest parallelWork3 = new OneTimeWorkRequest.Builder(ParallelCounterWorker.class)
                .setInputData(parallelInputData)
                .build();

        // Обработчик для последовательного счетчика
        findViewById(R.id.button1).setOnClickListener(v -> {
            WorkManager.getInstance(this)
                    .beginWith(work1)
                    .then(work2)
                    .then(work3)
                    .enqueue();
        });

        // Обработчик для параллельного счетчика
        findViewById(R.id.button2).setOnClickListener(v -> {
            List<OneTimeWorkRequest> parallelWorks = Arrays.asList(parallelWork1, parallelWork2, parallelWork3);
            WorkManager.getInstance(this)
                    .beginWith(parallelWorks)
                    .enqueue();
        });

        // Обработчик для загрузки изображения
        findViewById(R.id.button3).setOnClickListener(v -> {
            OneTimeWorkRequest imageWork = new OneTimeWorkRequest.Builder(ImageLoaderWorker.class)
                    .build();

            WorkManager.getInstance(this)
                    .beginWith(imageWork)
                    .enqueue();

            // Наблюдаем за результатом загрузки изображения
            WorkManager.getInstance(this)
                    .getWorkInfoByIdLiveData(imageWork.getId())
                    .observe(this, workInfo -> {
                        if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            String imageUrl = workInfo.getOutputData().getString(KEY_IMAGE_URL);
                            if (imageUrl != null) {
                                loadImage(imageUrl);
                            }
                        }
                    });
        });

        // Наблюдение за результатами последовательного счетчика
        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(work3.getId())
                .observe(this, workInfo -> {
                    if(workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        int finalCounter = workInfo.getOutputData().getInt(KEY_COUNTER, 0);
                        Log.d(TAG, "Sequential Final num: " + finalCounter);
                    }
                });

        // Наблюдение за результатами параллельного счетчика
        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(parallelWork1.getId())
                .observe(this, workInfo -> {
                    if(workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        int counter = workInfo.getOutputData().getInt(KEY_PARALLEL_COUNTER, 0);
                        Log.d(TAG, "Parallel Worker 1 count: " + counter);
                    }
                });

        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(parallelWork2.getId())
                .observe(this, workInfo -> {
                    if(workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        int counter = workInfo.getOutputData().getInt(KEY_PARALLEL_COUNTER, 0);
                        Log.d(TAG, "Parallel Worker 2 count: " + counter);
                    }
                });

        WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(parallelWork3.getId())
                .observe(this, workInfo -> {
                    if(workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        int counter = workInfo.getOutputData().getInt(KEY_PARALLEL_COUNTER, 0);
                        Log.d(TAG, "Parallel Worker 3 count: " + counter);
                    }
                });
    }

    private void loadImage(String imageUrl) {
        new Thread(() -> {
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                runOnUiThread(() -> {
                    imageView.setImageBitmap(bitmap);
                });
            } catch (Exception e) {
                Log.e(TAG, "Error loading image: " + e.getMessage());
                runOnUiThread(() -> {
                    Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
}



