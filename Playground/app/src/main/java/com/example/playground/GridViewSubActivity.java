package com.example.playground;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


public class GridViewSubActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_gridview);

        Intent intent = getIntent();
        int imageId = intent.getIntExtra("IMAGEID", 0);

        ImageView imageView = findViewById(R.id.image_view);
        imageView.setImageResource(imageId);

    }
}