package com.example.myproject.activities;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.R;
import com.example.myproject.service.BindService;
import com.example.myproject.service.BindService.LocalBinder;

public class BindServiceMainActivity extends AppCompatActivity {
    BindService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_main_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Bind to BindService
        Intent intent = new Intent(this, BindService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection); // クライアントをサービスからアンバインド
        mBound = false;
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalBinder binder = (LocalBinder) service; // onBind が呼ばれてインナークラス LocalBinder のインスタンスが返る
            mService = binder.getService(); // 本来の BindService を取得
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };
}
