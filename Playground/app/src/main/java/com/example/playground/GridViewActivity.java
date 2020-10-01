package com.example.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class GridViewActivity extends Activity implements AdapterView.OnItemClickListener {

    // 表示する画像の名前（拡張子無し）
    private String[] members = {"dd1", "dd2", "dd3"};

    // Resource ID を格納する array
    private List<Integer> imgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gridview);

        // for-each member 名を R.drawable.名前 として int に変換して array に登録
        for (String member : members) {
            int imageId = getResources().getIdentifier(member, "drawable", getPackageName());
            imgList.add(imageId);
        }

        // GridViewのインスタンスを生成
        GridView gridview = findViewById(R.id.gridview);
        // BaseAdapter を継承したGridAdapterのインスタンスを生成
        // 子要素のレイアウトファイル grid_items.xml を
        // activity_main_gridview.xml に inflate するために GridAdapter に引数として渡す
        GridAdapter adapter = new GridAdapter(this.getApplicationContext(),
                R.layout.grid_items,
                imgList,
                members
        );

        // gridViewにadapterをセット
        gridview.setAdapter(adapter);

        // item clickのListnerをセット
        gridview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplication(), GridViewSubActivity.class);
        intent.putExtra("IMAGEID", imgList.get(position));
        startActivity(intent);
    }
}
