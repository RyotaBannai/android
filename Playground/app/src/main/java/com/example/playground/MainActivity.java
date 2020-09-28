package com.example.playground;

import android.os.Bundle;

import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.ComponentActivity;

import android.widget.LinearLayout;

import android.os.Bundle;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.graphics.Color;

public class MainActivity extends ComponentActivity {
    private final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
//        setContentView(android.R.string.VideoView_error_button);
//        getWindow().setTitle(getResources().getText(R.string.app_name));
        TextView tv = new TextView(this);
        tv.setText("Hello", TextView.BufferType.EDITABLE);
        tv.setTextColor(Color.RED);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        setContentView(linearLayout);
        TextView tv2 = new TextView(this);
        tv2.setText("Color RGB[127, 127, 0]");
        tv2.setTextColor(Color.rgb(127, 127, 0));
        linearLayout.addView(tv, new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        linearLayout.addView(tv2, new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
    }
}