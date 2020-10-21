package com.example.myproject.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import com.example.myproject.R;
import com.example.myproject.tasks.MyAsyncTask;

public class AsyncTaskMainActivity extends ComponentActivity {

    private TextView textView;
    private Button button;
    private Button countButton;
    private MyAsyncTask task;
    private Integer count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        countButton = (Button) findViewById(R.id.countButton);

        task = new MyAsyncTask(textView, button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setEnabled(false);
                task.execute(1);
            }
        });

        /*
         * 先に AsyncTask を実行してから、メインスレッドでカウントアップできることを確認
         * */
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                ((Button) v).setText("Check: " + count.toString());
            }
        });
    }
}
