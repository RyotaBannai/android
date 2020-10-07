package com.example.playground.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.playground.R;

import android.widget.TextView;

/*
 * RelativeLayout を使いたいときは、merge tag 内のタグに relative 属性を追加すること
 * */

/*
 * reference: https://qiita.com/yst_i/items/5cc766dc95e2cfc97a40
 * */

public class CustomImageView extends LinearLayout {
    private Context mContext;

    /*
     * コンストラクタは3つ
     * API21 からは 4 つのコンストラクタが使える
     * */

    public CustomImageView(Context context) {
        this(context, null);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        setOrientation(VERTICAL); // must to set Orientation here.
//        this.setOrientation(LinearLayout.VERTICAL); // If you do programically like this.

        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate layout with merge tag
        View layout = inflater.inflate(R.layout.custom_image_view, this, true); // attachToRoot = true にすること
        ((TextView) layout.findViewById(R.id.merge_title)).setText("Default Title");
    }

    public void setInfo(String title, String description, String imageUrl) {
        ImageView imageView = findViewById(R.id.merge_icon);

        /*
         * Load Image from url
         * */
        Glide.with(getContext())
                .load(imageUrl)
                .apply(new RequestOptions().placeholder(R.drawable.dd1).fitCenter())
                .into(imageView);

        ((TextView) findViewById(R.id.merge_title)).setText(title);
        ((TextView) findViewById(R.id.merge_description)).setText(description);
        invalidate();
    }
}
