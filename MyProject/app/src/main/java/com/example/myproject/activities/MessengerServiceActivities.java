package com.example.myproject.activities;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myproject.R;
import com.example.myproject.services.MessengerService;

/**
 * reference: https://android.googlesource.com/platform/development/+/master/samples/ApiDemos/src/com/example/android/apis/app/MessengerServiceActivities.java
 */

public class MessengerServiceActivities {
    public static class Binding extends Activity {
        final String TAG = getClass().getSimpleName();
        private int counter = 0;

        Messenger mService = null;
        boolean mIsBound;
        TextView mCallBackText;

        class IncomingHandler extends Handler {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d(TAG, "Received Message from service.");
                switch (msg.what) {
                    case MessengerService.MSG_SET_VALUE:
                        mCallBackText.setText("Received from service:  " + msg.arg1);
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        }

        final Messenger mMessenger = new Messenger(new IncomingHandler());

        private ServiceConnection mConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "Service connected.");
                mService = new Messenger(service);
                mCallBackText.setText(R.string.service_attached);

                try {
                    // message に service に送りたいメッセージと、自分の参照を入れて、サービスに送る。
                    Message msg = Message.obtain(null, MessengerService.MSG_REGISTER_CLIENT);
                    msg.replyTo = mMessenger;
                    mService.send(msg);

                } catch (RemoteException e) {
                    Log.d(TAG, "Failed to register this activity to Service");
                }

                // As part of the sample, tell the user what happened.
                Toast.makeText(Binding.this, R.string.remote_service_connected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
                mCallBackText.setText(R.string.service_detached);
                Toast.makeText(Binding.this, R.string.remote_service_disconnected, Toast.LENGTH_SHORT).show();
            }
        };

        // callback for starting binding service
        void doBindService() {
            if(mIsBound) return;

            bindService(new Intent(Binding.this, MessengerService.class), mConnection, Context.BIND_AUTO_CREATE);
            mIsBound = true;
            mCallBackText.setText(R.string.service_binding);
        }

        // callback for finishing binding service
        void doUnbindService() {
            if (mIsBound) {
                if (mService != null) {
                    try {
                        Message msg = Message.obtain(null, MessengerService.MSG_UNREGISTER_CLIENT);
                        msg.replyTo = mMessenger;
                        mService.send(msg);
                    } catch (RemoteException e) {
                        Log.d(TAG, "Failed to un-register this activity from Service");
                    }
                }

                unbindService(mConnection); // onServiceDisconnected が呼ばれる
                mIsBound = false;
                mCallBackText.setText(R.string.service_unbinding);
            }
        }

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.two_way_comm_service_main_layout);

            Button bindButton = findViewById(R.id.bind);
            bindButton.setOnClickListener(mBindListener);
            Button unbindButton = findViewById(R.id.unbind);
            unbindButton.setOnClickListener(mUnbindListener);
            Button sendMSGButton = findViewById(R.id.sendMsg);
            sendMSGButton.setOnClickListener(mSendMSGListener);

            mCallBackText = findViewById(R.id.callback);
            mCallBackText.setText(R.string.service_not_attached);
        }

        private OnClickListener mBindListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                doBindService();
            }
        };

        private OnClickListener mUnbindListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                doUnbindService();
            }
        };

        private OnClickListener mSendMSGListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsBound && mService != null) {
                    try {
                        Message msg = Message.obtain(null, MessengerService.MSG_SET_VALUE, counter++, 0);
                        mService.send(msg);
                    } catch (RemoteException e) {
                        Log.d(TAG, "Failed to send message to service.");
                    }
                }
            }
        };
    }
}
