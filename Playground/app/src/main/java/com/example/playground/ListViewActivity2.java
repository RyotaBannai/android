package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ListViewActivity2 extends Activity {
    protected ListView listView;
    protected TextView titleText;

    private String[] names = {"Jobs", "Mark", "Gates"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main_activity2);

        if (savedInstanceState == null) {
            listView = findViewById(R.id.listview);
            titleText = findViewById(R.id.title);
        }

        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names));
        listView.setOnItemClickListener(new ClickEventListener(titleText)); // 匿名インナークラス
    }

    static class ClickEventListener implements ListView.OnItemClickListener {
        private final TextView textView;

        ClickEventListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Parent: クリックした項目の AdapterView この場合は ListView を使用しているため、ListView になる。※　ListView は AdapterView の子クラス。
            ListView listView = (ListView) parent;
            String item = (String) listView.getItemAtPosition(position);
            textView.setText(item);
        }
    }
}
