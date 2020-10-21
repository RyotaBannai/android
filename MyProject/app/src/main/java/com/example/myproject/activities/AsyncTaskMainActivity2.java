package com.example.myproject.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.tasks.ChangeOpacityAsyncTask;

public class AsyncTaskMainActivity2 extends Activity {
    private Button startButton;
    private TextView textView;
    private ImageView imageView_;
    private Bitmap image_;
    private ChangeOpacityAsyncTask task_;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_main2);

        //main.xmlに設定したコンポーネントをid指定で取得します。
        startButton = (Button) findViewById(R.id.startButton);
        textView = (TextView) findViewById(R.id.textView);

        // イメージの準備
        image_ = BitmapFactory.decodeResource(getResources(), R.drawable.jones);

        // 変換前のイメージを表示
        imageView_ = (ImageView) findViewById(R.id.imageView);
        imageView_.setImageBitmap(image_);

        // タスクの生成
        task_ = new ChangeOpacityAsyncTask(this, imageView_, textView);

        //startButtonがクリックされた時の処理を登録します。
        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Button) v).setEnabled(false);
                // 非同期処理を開始する
                task_.execute(image_);
            }
        });
    }
}