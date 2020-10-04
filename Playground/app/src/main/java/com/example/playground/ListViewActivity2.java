package com.example.playground;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class ListViewActivity2 extends Activity {
//    protected ListView listView;
//    protected TextView titleText;

    private String[] names = {"Jobs", "Mark", "Gates"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main_activity2);

        // インストール済みのアプリケーションを取得
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES);

        CardListAdapter adapter = new CardListAdapter(getApplicationContext());

        if (savedInstanceState == null) {
            for (PackageInfo info : packageInfoList) {
                adapter.add(info);
            }
        }

        int padding = (int) (getResources().getDisplayMetrics().density * 8);
        ListView listView = (ListView) findViewById(R.id.card_list);
        listView.setPadding(padding, 0, padding, 0);
        listView.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
        listView.setDivider(null); // ListView の境界線をなくす

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View header = inflater.inflate(R.layout.list_header_footer, listView, false);
        View footer = inflater.inflate(R.layout.list_header_footer, listView, false);
        /*
        * inflate の第三引数：第二引数で指定した View を root 要素とするかどうか。false なら、単純に第一引数で指定したレイアウトの RootView がそのまま Root になる。
        * */
        // 余白追加のための header/ footer
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);
        listView.setAdapter(adapter);

//        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names));
//        listView.setOnItemClickListener(new ClickEventListener(titleText)); // 匿名インナークラス
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
