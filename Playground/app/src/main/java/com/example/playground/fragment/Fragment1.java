package com.example.playground.fragment;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.playground.R;

public class Fragment1 extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*
        * システムが Fragment を作成したとき
        * */
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
        * Fragment の UI が描画されるとき
        * */
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    // onPause()
    /*
    * Fragmentが停止するときによばれる。Fragmentで変更されたステータスの保存はここで行う。
    * */
}