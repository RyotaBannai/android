package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import com.example.playground.adapter.GalleryImageAdapter;

public class GalleryActivity extends Activity {
    ImageView selectedImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gallery);

        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        selectedImage = (ImageView) findViewById(R.id.imageView);
        gallery.setSpacing(1);
        final GalleryImageAdapter galleryImageAdapter = new GalleryImageAdapter(this);
        gallery.setAdapter(galleryImageAdapter);

        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // show the selected Image
                // System.out.println(galleryImageAdapter.mImageIds[position]); // e.g. 2131230812, 2131230843 -> つまり Resource Id
                selectedImage.setImageResource(galleryImageAdapter.mImageIds[position]);
            }
        });
    }
}
