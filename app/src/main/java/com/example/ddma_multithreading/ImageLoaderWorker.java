package com.example.ddma_multithreading;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoaderWorker extends Worker {
    private static final String TAG = "ImageLoader";
    private static final String KEY_IMAGE_URL = "image_url";

    public ImageLoaderWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            // Получаем URL изображения из API
            URL url = new URL("https://random.dog/woof.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Читаем JSON ответ
            InputStream inputStream = connection.getInputStream();
            StringBuilder response = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.append(new String(buffer, 0, bytesRead));
            }
            inputStream.close();

            // Парсим JSON и получаем URL изображения
            JSONObject jsonResponse = new JSONObject(response.toString());
            String imageUrl = jsonResponse.getString("url");

            // Загружаем изображение
            URL imageUrlObj = new URL(imageUrl);
            HttpURLConnection imageConnection = (HttpURLConnection) imageUrlObj.openConnection();
            imageConnection.setRequestMethod("GET");
            InputStream imageStream = imageConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            imageStream.close();

            // Сохраняем URL изображения в выходных данных
            androidx.work.Data outputData = new androidx.work.Data.Builder()
                    .putString(KEY_IMAGE_URL, imageUrl)
                    .build();

            return Result.success(outputData);
        } catch (Exception e) {
            Log.e(TAG, "Error loading image: " + e.getMessage());
            return Result.failure();
        }
    }
} 