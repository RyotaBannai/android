package com.example.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import onactivityresult.ActivityResult;
import onactivityresult.OnActivityResult;

public class IntentActivity2 extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.playground.intent.MESSAGE";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_intent);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubIntentActivity.class);
                EditText editText = findViewById(R.id.editText);
                String message = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }

    @OnActivityResult(requestCode = REQUEST_CODE, resultCodes = {RESULT_OK})
    protected void onActivityResultActivityOk() {
        System.out.println("OK");
    }

    @OnActivityResult(requestCode = REQUEST_CODE, resultCodes = {RESULT_CANCELED})
    protected void onActivityResultActivityCanceled() {
        System.out.println("Canceled");
    }

    @OnActivityResult(requestCode = REQUEST_CODE)
    protected void onActivityResultTestActivityUserDefined(final int resultCode) {
        System.out.println("Catch all");
    }

}