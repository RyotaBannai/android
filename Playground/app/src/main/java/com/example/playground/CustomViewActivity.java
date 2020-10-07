package com.example.playground;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.playground.customView.CustomLayout;

public class CustomViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.painting_layout);

        setContentView(R.layout.custom_view_activity_main);
//        ((CustomLayout) findViewById(R.id.custom_layout1)).setmButtonName(2);

        // setContentView(new CustomLayout(getApplicationContext(), null)); // これも可
    }
}
