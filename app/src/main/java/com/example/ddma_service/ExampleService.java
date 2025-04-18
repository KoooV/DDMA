package com.example.ddma_service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ExampleService extends Service {
    private int counter = 0;
    private Handler handler;
    private Runnable runnable;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(Looper.getMainLooper());
        startCounter();
    }

    private void startCounter() {
        runnable = new Runnable() {
            @Override
            public void run() {
                counter++;
                sendBroadcast(counter);
                handler.postDelayed(this, 5000);
            }
        };
        handler.post(runnable);
    }

    private void sendBroadcast(int value) {
        Intent intent = new Intent("service_update");
        intent.putExtra("counter", value);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
