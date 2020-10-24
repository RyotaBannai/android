package com.example.myproject.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

public class LooperActivity extends Activity {
    final static String TAG = "LooperActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HandlerThread handlerThread = new HandlerThread("SubThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        Log.d(TAG, "handleMessage: OK");
                        break;
                    default:
                        Log.d(TAG, "Default");
                        break;
                }
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: " + Thread.currentThread().getName());
            }
        };
        Message msg = Message.obtain();
        msg.what = 2;
        handler.post(runnable);
        handler.sendMessage(msg);
    }
}
