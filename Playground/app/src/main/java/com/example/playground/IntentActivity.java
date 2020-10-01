package com.example.playground;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntentActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.playground.intent.MESSAGE";
    private static final int REQUEST_CODE = 1;

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
//                startActivity(intent);  // -> start intent w/o expecting returned value.
                startActivityForResult(intent, REQUEST_CODE); // int only
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // ref: https://qiita.com/kskso9/items/01c8bbb39355af9ec25e
        System.out.println("returned");
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (REQUEST_CODE):
                if (resultCode == RESULT_OK) {
                    // OK ボタンを押して戻ってきたときの処理
                    System.out.println("The result message is:" + data.getStringExtra("INPUT_STRING"));
                } else if (resultCode == RESULT_CANCELED) {
                    // キャンセルボタンを押して戻ってきたときの処理
                    System.out.println("Cancel button is clicked");
                } else {
                    System.out.println("Something wrong");
                }
                break;
            default:
                break;
        }
    }
}