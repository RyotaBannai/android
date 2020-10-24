package com.example.myproject.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Objects;

public class BootReceiver extends BroadcastReceiver {
    final static String TAG = "BootReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive.");
        if(Objects.equals(intent.getAction(), "android.intent.action.BOOT_COMPLETED")){
            Log.d(TAG, "Application successfully booted.");
            // Set the alarm here.
            // boot 完了まで少し時間かかる（1 分くらい）


            // 1 分後にデバイスのスリープを解除し、アラームを 1 回だけ（反復なし）トリガー
//            private AlarmManager alarmMgr;
//            private PendingIntent alarmIntent;
//            alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(context, AlarmReceiver.class);
//            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//
//            alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                    SystemClock.elapsedRealtime() + 60 * 1000, alarmIntent);
        }
    }
}
