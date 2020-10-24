package com.example.myproject.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myproject.receivers.BootReceiver;

public class TestAlarmActivity extends Activity {
    final static String TAG = "TestAlarmActivity";
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        context = getApplicationContext();
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        /**
         *  レシーバをプログラムで有効にした場合、再起動後もマニフェストの設定より優先される
         *  レシーバは、アプリによって無効にされるまで有効。
         **/
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }
}
