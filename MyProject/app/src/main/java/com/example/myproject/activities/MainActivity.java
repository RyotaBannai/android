package com.example.myproject.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.myproject.R;
import com.example.myproject.workers.SayHelloWorker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Context myContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        myContext = getApplicationContext();
        doWork();
    }

    private void doWork() {
        // v1 //
        // WorkRequest sayHelloRequest = new OneTimeWorkRequest.Builder(SayHelloWorker.class).build(); // WorkRequest とそのサブクラスは、作業を実行する方法とタイミングを定義
        /*
         * 今回の場合のような単純な定義の場合は
         * OneTimeWorkerRequest.from(SayHelloWorker.class); とする
         *
         * Builder を使うときは様々な制約を設ける時に使用
         * */
        // v2 //
        // 注: 定義可能な最小繰り返し間隔は 15 分 JobScheduler API と同じ
        WorkRequest sayHelloRequest = new PeriodicWorkRequest.Builder(SayHelloWorker.class, 15, TimeUnit.MINUTES).build();
        WorkManager.getInstance(myContext).enqueue(sayHelloRequest); // enqueue() メソッドを使用して WorkRequest を WorkManager に送信
    }
}
