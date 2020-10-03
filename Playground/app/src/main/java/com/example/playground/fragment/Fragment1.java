package com.example.playground.fragment;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.playground.R;

public class Fragment1 extends Fragment {

    /*
    * Constants
    * */
    private final static String KEY_NAME = "key_name";
    private final static String KEY_BACKGROUND_COLOR = "key_background_color";

    /*
    * Members
    * */
    private String mName = "";
    private @ColorInt int mBackgroundColor = Color.TRANSPARENT;

    /*
    * View
    * */
    private TextView mTextView;

    /*
    * fragment に引数を渡せるようにする
    * */
    @CheckResult // このアノテーションをつけたメソッドの戻り値（ここではこのクラスのインスタンス）を使用することを強制する
    public static Fragment1 createInstance(String name, @ColorInt int color) { // リソース ID なのか色の Int 値なのか区別を付けるため
        Fragment1 fragment = new Fragment1();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_NAME, name);
        bundle.putInt(KEY_BACKGROUND_COLOR, color);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*
         * システムが Fragment を作成したとき
         * */
        super.onCreate(savedInstanceState);

        /*
        * 引数は onCreate で受け取る
        * */

        Bundle bundle = getArguments();
        if(bundle != null){
            mName = bundle.getString(KEY_NAME);
            mBackgroundColor = bundle.getInt(KEY_BACKGROUND_COLOR);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
         * Fragment の UI が描画されるとき
         * */
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = view.findViewById(R.id.textView);
        mTextView.setText(mName);
        view.setBackgroundColor(mBackgroundColor);
    }

    public static boolean allowBackPressed(){
        return true;
    }

    // onPause()
    /*
     * Fragmentが停止するときによばれる。Fragmentで変更されたステータスの保存はここで行う。
     * */
}