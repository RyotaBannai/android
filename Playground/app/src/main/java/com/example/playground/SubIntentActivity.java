package com.example.playground;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

public class SubIntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity_intent);

        Intent intent = getIntent();
        String message = intent.getStringExtra(IntentActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.text);
        textView.setText(message);
    }
}
