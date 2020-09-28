package com.example.playground;

import android.os.Bundle;

import androidx.annotation.Nullable;
// import androidx.appcompat.app.AppCompatActivity;
// import androidx.activity.ComponentActivity;
import android.app.Activity;

import android.widget.LinearLayout;

import android.os.Bundle;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.graphics.Color;
import android.graphics.Typeface;



public class MainActivity extends Activity {
    private final int WRAP_CONTENT = LayoutParams.WRAP_CONTENT; //
    private final int MATCH_PARENT = LayoutParams.MATCH_PARENT; //

    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
//        setContentView(android.R.string.VideoView_error_button);
//        getWindow().setTitle(getResources().getText(R.string.app_name));

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView tv = new TextView(this);
        tv.setText("Hello", TextView.BufferType.EDITABLE);
        tv.setTextColor(Color.RED);
        tv.setBackgroundColor(Color.LTGRAY);
        tv.setTextSize(20.0f);
        tv.setTypeface(Typeface.MONOSPACE);

        setContentView(linearLayout);
        TextView tv2 = new TextView(this);
        tv2.setText("Color RGB[127, 127, 0]");
        tv2.setTextColor(Color.rgb(127, 127, 0));
        tv2.setHeight(100);
        tv2.setBackgroundColor(Color.DKGRAY);
        tv2.setPadding(5, 5, 5, 5);
        tv2.setGravity(Gravity.CENTER); // centering both in vertically and horizontally.
        linearLayout.addView(tv, new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)); // width, height
        linearLayout.addView(tv2, new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)); // WRAP_CONTENT は 自動調整はしないで最低限表示に必要なスペースを確保する
    }
}