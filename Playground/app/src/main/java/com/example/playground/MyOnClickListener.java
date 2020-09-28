package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class MyOnClickListener extends Activity implements OnClickListener {

    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int count;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        count = 0;
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        setContentView(linearLayout);

        button = new Button(this);
        button.setText("Count is:" + count);
        button.setOnClickListener(this); // OnClickListener interface を実装しているため this にする
        linearLayout.addView(button, new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
    }

    @Override
    public void onClick(View v) {
        if (v == button) {
            count++;
            button.setText("Count is: " + count);
        }
    }
}
