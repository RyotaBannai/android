package com.example.myproject.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myproject.R;

import java.util.ArrayList;

/**
 * reference: https://android.googlesource.com/platform/development/+/master/samples/ApiDemos/src/com/example/android/apis/app/MessengerService.java
 */

public class MessengerService extends Service {

    // For showing and hiding our notification
    NotificationManager mNM;
    // Keeps track of all current registered clients
    ArrayList<Messenger> mClients = new ArrayList<>();
    // Holds last value set by a client
    int mValue = 0;


    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_UNREGISTER_CLIENT = 2; // Command to the service to unregister a client, to stop receiving callbacks from the service.
    static final int MSG_SET_VALUE = 3;

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    mClients.remove(msg.replyTo);
                    break;
                case MSG_SET_VALUE:
                    mValue = msg.arg1;
                    for (int i = mClients.size(); i > 0; i--) {
                        try {
                            mClients.get(i).send(Message.obtain(null, MSG_SET_VALUE, mValue, 0));
                        } catch (RemoteException e) {
                            mClients.remove(i);
                        }
                    }
            }
        }
    }

    // Target we publish for clients to send messages to IncomingHandler
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    @Override
    public void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification();
    }

    @Override
    public void onDestroy() {
        // cancel the persistent notification
        mNM.cancel(R.string.remote_service_started);
        Toast.makeText(this, R.string.remote_service_stopped, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    // show notification while this service is running.
    private void showNotification() {
        CharSequence text = getText(R.string.remote_service_started);
        PendingIntent contentIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, Controller.this), 0); // TODO: replace Activity with another.

        Notification notification = new Notification.Builder(this)
//                .setSmallIcon(R.drawable.stat_sample)  // the status icon
                .setTicker(text)  // the status text
                .setWhen(System.currentTimeMillis())  // the time stamp
                .setContentTitle(getText(R.string.local_service_label))  // the label of the entry
                .setContentText(text)  // the contents of the entry
                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
                .build();
        mNM.notify(R.string.remote_service_started, notification);
    }
}
