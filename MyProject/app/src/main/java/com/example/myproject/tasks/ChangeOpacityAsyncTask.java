package com.example.myproject.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangeOpacityAsyncTask extends AsyncTask<Bitmap, Integer, Bitmap>  {

    private ImageView imageView;
    private TextView textView;
    private ProgressDialog progressDialog;
    private Activity uiActivity;

    public ChangeOpacityAsyncTask(Activity activity, ImageView iv, TextView tv) {
        super();
        uiActivity = activity;
        imageView = iv;
        textView = tv;
    }

    // 事前準備の処理
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(uiActivity); // ここで main の UI が欲しい
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(Bitmap... bitMap) {
        Bitmap outBitmap = bitMap[0].copy(Bitmap.Config.ARGB_8888, true); // isMutable 変更可能

        int width_ = outBitmap.getWidth();
        int height_ = outBitmap.getHeight();
        int totalPixel = width_ * height_;
        progressDialog.setMax(totalPixel);

        int i, j;
        for (j = 0; j < height_; j++) {
            for (i = 0; i < width_; i++) {
                int pixelColor = outBitmap.getPixel(i, j);
                outBitmap.setPixel(i, j, Color.argb(60, Color.red(pixelColor), Color.green(pixelColor), Color.blue(pixelColor)));
            }
            // publishProgress メソッドを呼ぶことで
            // onProgressUpdate メソッドが呼ばれ、進捗状況がUIスレッドで表示される
            publishProgress(i + j);
        }
        return outBitmap;
    }

    // 進捗状況を UI スレッドで表示する

    @Override
    protected void onProgressUpdate(Integer... progress) {
        progressDialog.incrementProgressBy(progress[0]);
    }

    // バックグランド処理が完了し、UI スレッドに反映する
    @Override
    protected void onPostExecute(Bitmap result) {
        progressDialog.dismiss();
        imageView.setImageBitmap(result);
        textView.setText("AsyncTask is done.");
    }
}