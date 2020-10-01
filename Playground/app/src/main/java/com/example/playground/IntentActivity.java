package com.example.playground;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class IntentActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.playground.intent.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_intent);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("clicked");
                Intent intent = new Intent(getApplicationContext(), SubIntentActivity.class);
                EditText editText = findViewById(R.id.editText);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }
}