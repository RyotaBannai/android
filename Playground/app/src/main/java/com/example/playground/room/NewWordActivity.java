package com.example.playground.room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playground.R;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLAY = "com.example.playground.wordlistsql.REPLY";

    private EditText mEditWordView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replayIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replayIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replayIntent.putExtra(EXTRA_REPLAY, word);
                    setResult(RESULT_OK, replayIntent);
                }
                finish();
            }
        });
    }
}
