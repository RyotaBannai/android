package com.example.playground;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

public class SubIntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity_intent);

        final Intent intent = getIntent();
        String message = intent.getStringExtra(IntentActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.text);
        textView.setText(message);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_sub = new Intent();
                intent_sub.putExtra("INPUT_STRING", "Checked the message. Ty.");
                setResult(RESULT_OK, intent_sub);
                finish();
            }
        });
    }
}
