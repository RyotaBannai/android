package com.example.myproject.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;
import com.example.myproject.services.MyService;

public class ServiceMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_main_layout);

//        Notification notification = new Notification(
//                R.drawable.ic_launcher_background,
//                getText(R.string.ticket_text),
//                System.currentTimeMillis());

        Button startButton = findViewById(R.id.start_service_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("REQUEST_CODE", 1);

                // Serviceの開始
                // startService(intent);
                startForegroundService(intent); // API level 26 +
            }
        });

        Button stopButton = findViewById(R.id.stop_service_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                stopService(intent);
            }
        });

    }
}
