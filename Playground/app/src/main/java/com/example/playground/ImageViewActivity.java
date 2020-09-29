package com.example.playground;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.res.Resources;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ImageViewActivity extends Activity {
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get image
        Resources r = getResources();
        Bitmap bmp = new BitmapFactory().decodeResource(r, R.drawable.daredevil);
        ImageView imgView = new ImageView(this);
        imgView.setImageBitmap(bmp);

        // title
        TextView tv = new TextView(this);
        tv.setText("I'm the DareDevil!!");

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(tv, createParam(WC, WC)); // no need of params for layout. only view needs params.
        linearLayout.addView(imgView, createParam(WC, WC));
        setContentView(linearLayout);
    }

    private LinearLayout.LayoutParams createParam(int w, int h) {
        return new LinearLayout.LayoutParams(w, h);
    }
}
