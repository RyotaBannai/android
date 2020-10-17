package com.example.myproject.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ArrayCreatingInputMerger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.example.myproject.R;
import com.example.myproject.workers.SayHelloWorker;
import com.example.myproject.workers.SayGoodMorningWorker;

import java.util.Arrays;

public class ChainMainActivity extends AppCompatActivity {
    private Context myContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        myContext = getApplicationContext();

        setChain();
    }

    private void setChain() {
        OneTimeWorkRequest requestGoodMorning = new OneTimeWorkRequest.Builder(SayGoodMorningWorker.class)
                .setInputMerger(ArrayCreatingInputMerger.class)
                .build();
        OneTimeWorkRequest requestHello = new OneTimeWorkRequest.Builder(SayHelloWorker.class)
                .setInputMerger(ArrayCreatingInputMerger.class)
                .build();

        WorkManager.getInstance(myContext)
                .beginWith(Arrays.asList(requestGoodMorning))
                .then(requestHello)
                .enqueue();
    }
}
