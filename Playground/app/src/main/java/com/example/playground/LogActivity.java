package com.example.playground;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.MessageFormat;

public class LogActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        System.out.println("Hello"); // you can check values in android studio Run tab
//        Log.d("ImageViewActivity2", "Hello in Logcat"); // display log both in Logcat tab and in Run tab

        /**
         * ログレベルを指定し出力するログをフィルタリングする
         * > Log.isLoaggableでは出力すべきログレベルを判定することはできますが、android.util.Logから出力すべきログレベルを設定することができません。
         *
         * $ adb devices # -> emulator を見つける
         * $ adb -s emulator-5554  shell # -> シェルを起動
         * $ setprop log.tag.MainActivity VERBOSE # -> タグで指定し、ログレベルを設定する
         *
         * ref: https://qiita.com/kaleidot725/items/cbf2de28dcd5bc848a35
         * */

        String pattern = "{0} : {1}";
        Log.println(Log.ASSERT, "MainAcitivty", MessageFormat.format(pattern, "VERBOSE", Log.isLoggable("MainActivity", Log.VERBOSE)));
        Log.println(Log.ASSERT, "MainAcitivty", MessageFormat.format(pattern, "DEBUG", Log.isLoggable("MainActivity", Log.DEBUG)));
        Log.println(Log.ASSERT, "MainAcitivty", MessageFormat.format(pattern, "INFO", Log.isLoggable("MainActivity", Log.INFO)));
        Log.println(Log.ASSERT, "MainAcitivty", MessageFormat.format(pattern, "WARN", Log.isLoggable("MainActivity", Log.WARN)));
        Log.println(Log.ASSERT, "MainAcitivty", MessageFormat.format(pattern, "ERROR", Log.isLoggable("MainActivity", Log.ERROR)));
        Log.println(Log.ASSERT, "MainAcitivty", MessageFormat.format(pattern, "ASSERT", Log.isLoggable("MainActivity", Log.ASSERT)));
    }
}
