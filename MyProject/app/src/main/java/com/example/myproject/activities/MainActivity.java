package com.example.myproject.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.myproject.R;
import com.example.myproject.workers.SayHelloWorker;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        doWork();
    }

    private void doWork() {
        WorkRequest sayHelloRequest = new OneTimeWorkRequest.Builder(SayHelloWorker.class).build(); // WorkRequest とそのサブクラスは、作業を実行する方法とタイミングを定義
        WorkManager.getInstance(getApplicationContext()).enqueue(sayHelloRequest); // enqueue() メソッドを使用して WorkRequest を WorkManager に送信
    }
}
