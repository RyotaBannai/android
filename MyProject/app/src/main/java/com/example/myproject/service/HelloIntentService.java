package com.example.myproject.service;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myproject.R;

/*
* 参考：
* - IntentService -> https://akira-watson.com/android/intentservice.html
* - SoundPool -> https://akira-watson.com/android/soundpool.html
* */

public class HelloIntentService extends IntentService {

    final static String TAG = "HelloIntentService";
    private SoundPool soundPool;
    private int soundOne;
    private boolean flg = false;

    public HelloIntentService() {
        super("HelloIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(1)
                .build();

        // ロードしておく
        soundOne = soundPool.load(this, R.raw.ensooniq, 1);
        // load が終わったか確認する場合
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0) { // 0 means success
                    flg = true;
                }
            }
        });
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent()");
        try {
//            for (int i = 0; i < 10; i++) {
//                Thread.sleep(1000);
//                Log.d(TAG, "sleep: " + String.valueOf(i));
//            }
            int count = 0;
            do {
                Thread.sleep(10);
                Log.d(TAG, "sleep: " + String.valueOf(count));
                count++;
            } while (!flg);

            Log.d(TAG, "start sound pool");
            soundPool.play(soundOne, 1.0f, 1.0f, 0, 0, 1);
            Log.d(TAG, "end sound pool");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();
    }
}
