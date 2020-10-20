package com.example.myproject.activities;


import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myproject.R;
import com.example.myproject.service.MessengerService;

/**
 * reference: https://android.googlesource.com/platform/development/+/master/samples/ApiDemos/src/com/example/android/apis/app/MessengerServiceActivities.java
 */

public class MessengerServiceActivities {
    public static class Binding extends Activity {

        Messenger mService = null;
        boolean mIsBound;
        TextView mCallBackText;

        class IncomingHandler extends Handler {
            @Override
            public void handleMessage(@NonNull Message msg) {
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

        private ServiceConnection Connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = new Messenger(service);
                mCallBackText.setText("Attached.");

                try {
                    Message msg = Message.obtain(null, MessengerService.MSG_REGISTER_CLIENT);
                    msg.replyTo = mMessenger;
                    mService.send(msg);


                } catch (RemoteException e) {

                }

                // As part of the sample, tell the user what happened.
                Toast.makeText(Binding.this, R.string.remote_service_connected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mService = null;
                mCallBackText.setText("Disconnected.");
                Toast.makeText(Binding.this, R.string.remote_service_disconnected, Toast.LENGTH_SHORT).show();
            }
        };


    }
}
