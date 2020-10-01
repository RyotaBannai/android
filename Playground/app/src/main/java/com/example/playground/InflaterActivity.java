package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InflaterActivity extends AppCompatActivity {
    private LinearLayout root; // inflate を埋め込むための main_activity にある View 用
    private boolean isButtonClicked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_infrator);

        root = findViewById(R.id.inflate_root);

        TextView textView = findViewById(R.id.inflate_text);
        textView.setText(R.string.contents);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isButtonClicked) {
                    getLayoutInflater().inflate(R.layout.inflate_layout, root); // 1, inflate したい layout, 2, inflate を埋め込む先の view
                    isButtonClicked = true;
                } else {
                    root.removeAllViews(); // inflate した layout を全て削除
                    isButtonClicked = false;
                }
            }
        });
    }
}
