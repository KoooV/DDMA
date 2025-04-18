package com.example.ddma_service;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView tvServiceCounter, tvDate, tvTime;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getIntExtra("counter", 0);
            tvServiceCounter.setText("Counter: " + counter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvServiceCounter = findViewById(R.id.tvServiceCounter);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);

        setupButtons();
    }

    private void setupButtons() {
        Button btnStart = findViewById(R.id.btnStartService);
        Button btnStop = findViewById(R.id.btnStopService);
        Button btnAlert = findViewById(R.id.btnAlertDialog);
        Button btnDate = findViewById(R.id.btnDatePicker);
        Button btnTime = findViewById(R.id.btnTimePicker);
        Button btnCustom = findViewById(R.id.btnCustomDialog);

        btnStart.setOnClickListener(v -> startService(new Intent(MainActivity.this, ExampleService.class)));
        btnStop.setOnClickListener(v -> stopService(new Intent(MainActivity.this, ExampleService.class)));

        btnAlert.setOnClickListener(v -> showAlertDialog());
        btnDate.setOnClickListener(v -> showDatePicker());
        btnTime.setOnClickListener(v -> showTimePicker());
        btnCustom.setOnClickListener(v -> showCustomDialog());
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Confirm action");
        builder.setMessage("Do you want to go to the second screen or save data?");
        builder.setIcon(android.R.drawable.ic_dialog_info); // Ícono del sistema (o usa el tuyo)


        builder.setPositiveButton("Go to SecondActivity", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });


        builder.setNeutralButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(getResources().getColor(R.color.white));
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(this, (DatePicker view, int year, int month, int day) ->
                tvDate.setText("Date: " + day + "/" + (month + 1) + "/" + year),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, (TimePicker view, int hour, int minute) ->
                tvTime.setText("Hour: " + hour + ":" + minute),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        ).show();
    }

    private void showCustomDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null);
        EditText etInput = dialogView.findViewById(R.id.etCustomInput);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("Custom input ")
                .setPositiveButton("Accept", (dialog, which) ->
                        Toast.makeText(this, "Texto: " + etInput.getText(), Toast.LENGTH_SHORT).show())
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(receiver, new IntentFilter("service_update"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}