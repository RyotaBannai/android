package com.example.myproject.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Objects;

import static com.example.myproject.activities.LocalBroadcastActivity.ACTIVITY_ACTION;

public class BroadcastService extends Service {
    public final static String SERVICE_ACTION = "SERVICE_ACTION";
    final static String TAG = BroadcastService.class.getSimpleName();
    private BroadcastReceiver receiver;
    LocalBroadcastManager localBroadcastManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "BroadCastService created.");
        localBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive");
                if (Objects.equals(intent.getAction(), ACTIVITY_ACTION)) { // Objects.equals(first, second)
                    Intent newIntent = new Intent();
                    newIntent.setAction(SERVICE_ACTION);
                    localBroadcastManager.sendBroadcast(newIntent);
                }
            }
        };
        IntentFilter filter = new IntentFilter(ACTIVITY_ACTION);
//        filter.addAction(ACTIVITY_ACTION);
        localBroadcastManager.registerReceiver(receiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(receiver);
    }

    private final IBinder binder = new BroadcastService.LocalBinder();

    public class LocalBinder extends Binder {
        public BroadcastService getService() {
            return BroadcastService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
