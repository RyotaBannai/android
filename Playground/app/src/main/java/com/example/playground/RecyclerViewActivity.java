package com.example.playground;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler_activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view); // find in layout
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.createDataset());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this); // レスポンシブにデータを表示するために使用
        rv.setHasFixedSize(true); // 表示するデータが固定長であれば、リソースを有効活用し、パフォーマンスを向上させる設定
        rv.setLayoutManager(linearLayoutManager);
//        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        rv.setAdapter(adapter);

    }

    private List<RowData> createDataset() {
        List<RowData> dataSet = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            RowData data = new RowData();
            data.setTitle("カサレアル　太郎" + i + "号");
            data.setDetails("カサレアル　太郎は" + i + "個の唐揚げが好き");

            dataSet.add(data);
        }
        return dataSet;
    }
}
