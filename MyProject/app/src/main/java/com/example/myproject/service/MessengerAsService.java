package com.example.myproject.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessengerAsService extends Service {
    public static final int MSG_SAY_HELLO = 1;
    private static final String TAG = "MessengerAsService";
    private static int counter = 0; // how many time receive messages

    /*
     * Handler of incoming messages from clients
     * */
    static class IncomingHandler extends Handler {
        private Context applicationContext;

        IncomingHandler(Context context) {
            applicationContext = context.getApplicationContext();
        }

        /*
         * Handler の handleMessage() メソッドが、サービスが Message を受け取る場所であり、
         * what メンバーに基づいてその後の操作を決める
         * */
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            counter++;
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(applicationContext, String.valueOf(counter) + "th hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    public Messenger mMessenger;

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind;");
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        mMessenger = new Messenger(new IncomingHandler(this));
        return mMessenger.getBinder();
    }

//    @Override
//    public boolean onUnbind(Intent intent) {
//        return super.onUnbind(intent);
//    }
}
