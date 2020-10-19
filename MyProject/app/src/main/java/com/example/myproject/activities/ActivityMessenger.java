package com.example.myproject.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myproject.R;
import com.example.myproject.service.MessengerAsService;

public class ActivityMessenger extends Activity implements View.OnClickListener {
    Messenger mService = null;
    boolean bound;
    private static final String TAG = "ActivityMessenger";

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "Connected");
            mService = new Messenger(service);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "Connection failed");
            mService = null;
            bound = false;
        }
    };

    public void sayHello(View v) {
        if (!bound) return;
        Message msg = Message.obtain(null, MessengerAsService.MSG_SAY_HELLO, 0, 0);
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_main_layout);
        findViewById(R.id.start_service_button).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, MessengerAsService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "Clicked");
        sayHello(v);
    }
}
