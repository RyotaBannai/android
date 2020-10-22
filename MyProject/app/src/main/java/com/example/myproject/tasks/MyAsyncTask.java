package com.example.myproject.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


/*
 * 参考：https://dev.classmethod.jp/articles/asynctask/
 * */
public class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {
    /**
     * ジェネリクスの指定がInteger, Integer, Integerとなっている
     * doInBackground メソッドの引数の型
     * onProgressUpdate メソッドの引数の型
     * onPostExecute メソッドの引数の型 (doInBackground 戻り値の型同じ出ないといけない)
     */

    final String TAG = getClass().getSimpleName();
    private TextView textView;
    private Button button;

    public MyAsyncTask(TextView textView, Button button) {
        super();
        this.textView = textView;
        this.button = button;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Integer doInBackground(Integer... value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        Log.d(TAG, String.valueOf(value));
        return value[0];
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer result) {
//        super.onPostExecute(o);
        textView.setText(String.valueOf(result));
        button.setEnabled(true);
    }
}
