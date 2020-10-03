package com.example.playground;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class RelativeLayoutActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_main_layout);
    }
}
