package com.example.myproject.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;

public class SayHelloWorker extends Worker {
    final static String TAG = "SayHelloWorker";
    private Random rand = new Random();
    private int upperBound = 10;
    private static final String PROGRESS = "PROGRESS";

    public SayHelloWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
        setProgressAsync(new Data.Builder().putInt(PROGRESS, 0).build());
    }

    @NonNull
    @Override
    public Result doWork() {
//        getInputData().getString("IMAGE_URL"); // get input data
        int int_random = rand.nextInt(upperBound);
        if (int_random % 2 == 0) {
            // Do the work here--in this case, Log
            sayHello();
            // Indicate whether the work finished successfully with the Result

            setProgressAsync(new Data.Builder().putInt(PROGRESS, 100).build());
            return Result.success();
        } else {
            return Result.failure();
        }
        /*
         * Result.retry(): 作業が失敗したため、再試行ポリシーにしたがって別の時間に再試行される
         *
         * */
    }

    public void sayHello() {
        Log.d(TAG, "Hello");
    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.d(TAG, TAG + "has stopped!");
    }
}
