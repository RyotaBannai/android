package com.example.myproject.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myproject.services.BroadcastService;

public class LocalBroadcastActivity extends AppCompatActivity {
    public final static String ACTIVITY_ACTION = "ACTIVITY_ACTION";
    final static String TAG = LocalBroadcastActivity.class.getSimpleName();
    public LocalBroadcastManager localBroadcastManager;
    boolean mBound = false;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "receive:" + intent.getAction());
        }
    };

    private void sendSyncBroadcast() {
        Intent intent = new Intent();
        intent.setAction(ACTIVITY_ACTION);
        localBroadcastManager.sendBroadcast(intent);
        Log.d(TAG, "sendSyncBroadcast");
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBound = true;
            Log.d(TAG, "onServiceConnected");
            sendSyncBroadcast();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.d(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BroadcastService.SERVICE_ACTION); // only from BroadCastService
        localBroadcastManager.registerReceiver(receiver, filter);
        if (!mBound) {
            Intent intent = new Intent(this, BroadcastService.class);
            // intent.putExtra("data","Notice me senpai!");
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        localBroadcastManager.unregisterReceiver(receiver); // do not forget to unregister receiver
    }
}
