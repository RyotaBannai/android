package com.example.myproject.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import com.example.myproject.R;

public class SPMainActivity extends ComponentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        // 書き込み
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("string", "value");
        editor.putBoolean("boolean", true);
        editor.putInt("int", 1);
        editor.putLong("long", 1);
        editor.putFloat("float", 1.0f);
        editor.apply();

        // 読み込み
        String str = prefs.getString("string", "");
        boolean bool = prefs.getBoolean("boolean", false);
        int intNum = prefs.getInt("int", 0);
        long longNum = prefs.getLong("long", 0);
        float floatNum = prefs.getFloat("float", 0.0f);
    }
}
