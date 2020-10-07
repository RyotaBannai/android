package com.example.playground;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.playground.customView.CustomImageView;

public class CustomViewMainActivity extends Activity {
    String imageUrl = "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRIdHgxdEH71SlZqK6-b4scTvnE2HkgHVnYbjIa5BzvDPjnFS-y";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view_main_layout);

        CustomImageView customImageView = findViewById(R.id.custom_view);
        customImageView.setInfo("title", "description", imageUrl);
    }
}
