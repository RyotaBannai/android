package com.example.myproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class BindService extends Service {
    // Binder given to clients
    private final IBinder binder = new LocalBinder();
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        // Return this instance of BindService so clients can call public methods
        public BindService getService() {
            return BindService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(10);
    }
}
