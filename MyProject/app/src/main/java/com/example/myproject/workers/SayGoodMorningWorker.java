package com.example.myproject.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;

public class SayGoodMorningWorker extends Worker {
    final static String TAG = "SayHelloWorker";
    private Random rand = new Random();

    public SayGoodMorningWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        int upperBound = 10;
        int int_random = rand.nextInt(upperBound);
        if (int_random % 2 == 0) {
            sayHi();
            return Result.success();
        } else {
            return Result.failure();
        }
    }

    public void sayHi() {
        Log.d(TAG, "Good Morning");
    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.d(TAG, TAG + "has stopped!");
    }
}
