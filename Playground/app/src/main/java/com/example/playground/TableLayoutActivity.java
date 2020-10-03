package com.example.playground;
import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class TableLayoutActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_main_layout);
    }
}
