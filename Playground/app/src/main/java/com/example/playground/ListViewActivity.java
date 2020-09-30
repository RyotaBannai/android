package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ListViewActivity extends Activity {
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;

    private String[] data = {"Tokyo", "Osaka", "Nagoya", "Sapporo"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        setContentView(linearLayout);

        ListView list = new ListView(this);
        linearLayout.addView(list, createParam(150, WC));

        /**
         * ArrayAdapter:
         * 2: TextView などデータを表示するために使用する view の Resource id
         * 3: ListView で表示したいデータリスト
         * */

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.rowdata, data);
        list.setAdapter(arrayAdapter);


    }

    private LinearLayout.LayoutParams createParam(int w, int h) {
        return new LinearLayout.LayoutParams(w, h);
    }
}
