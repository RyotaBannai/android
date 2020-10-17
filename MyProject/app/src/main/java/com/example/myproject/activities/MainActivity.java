package com.example.myproject.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.myproject.R;
import com.example.myproject.workers.SayHelloWorker;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Context myContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        myContext = getApplicationContext();
        try {
            doWork();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doWork() throws ExecutionException, InterruptedException {
        String WorkerTAG = "SAY_HELLO";
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
        PeriodicWorkRequest sayHelloRequest = new PeriodicWorkRequest
                .Builder(SayHelloWorker.class, 15, TimeUnit.MINUTES)
                .addTag(WorkerTAG)
                .build();

//        WorkManager.getInstance(myContext).enqueue(sayHelloRequest); // enqueue() メソッドを使用して WorkRequest を WorkManager に送信

        // Cancel all work before set new one just in case
        WorkManager.getInstance(myContext).cancelAllWork(); // Upon cancellation, ListenableWorker.onStopped() will be invoked for any affected workers.
        WorkManager.getInstance(myContext).enqueueUniquePeriodicWork(
                "say_hello", // or just add WorkerTAG
                ExistingPeriodicWorkPolicy.REPLACE,
                sayHelloRequest);

        /*
         * set input data:
         * .setInputData(
         *        new Data.Builder()
         *          .putString("IMAGE_URI", "http://...")
         *           .build()
         *  )
         * 戻り値を渡すこともできる
         * */

        ListenableFuture<WorkInfo> info = WorkManager.getInstance(myContext).getWorkInfoById(sayHelloRequest.getId());
        /*
         * ListenableFuture<WorkInfo> — aFuture that allows adding the listener which will be notified when the work with the specific id completes.
         * */
//        info.addListener(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(myContext, "", Toast.LENGTH_SHORT).show();
//            }
//        }, Executor);
        // Toast.makeText(myContext, String.format("%b",info.get().getState().isFinished()), Toast.LENGTH_SHORT).show();

        /*
         * Worker の状態変化をモニタリング
         * */
        WorkManager.getInstance(myContext).getWorkInfoByIdLiveData(sayHelloRequest.getId()).observe(this, workInfo -> {
            if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                Toast.makeText(myContext, String.format("%b", workInfo.getState().isFinished()), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(myContext, "not finished yet ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
