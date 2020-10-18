package com.example.myproject.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.myproject.R;

public class MyService extends Service {
    // onBind() で返す Binder
//    private MyBinder binder = new MyBinder();

    final static String TAG = "MyService";
    private MediaPlayer mediaPlayer;
    private int counter = 0;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);
    }

    /*
     * Once the service is started, the onStartCommand(intent) method is called.
     * It passes in the Intent object from the startService(intent) call of the referer.
     * */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Log.d(TAG, "onStartCommand");
        Context context = getApplicationContext();
        int requestCode = intent.getIntExtra("REQUEST_CODE", 0);
        String channelId = "default";
        String title = context.getString(R.string.app_name);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_DEFAULT);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(context, channelId)
                    .setSmallIcon(android.R.drawable.ic_media_play) // on the status bar
                    .setContentText("MediaPlay") // details on the notification
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent) // ユーザーが Notification をタップした際に Activity を起動する Intent
                    .setWhen(System.currentTimeMillis())
                    .build();

            startForeground(1, notification); // 通知の ID に0を指定すると、通知が表示されないので注意
            audioStart();
        }


        return Service.START_NOT_STICKY;
        // this is the restart option: this means service is restarted if it gets terminated by android system.
        // original Intent won't be delivered while START_REDELIVER_INTENT will do.
    }

    private void audioStart() {
        counter++;
        Log.d(TAG, "audioStart:" + String.valueOf(counter));

        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

            String str = "Start playing an audio";
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.d(TAG, "The end of audio");
                    audioStop();
                    stopSelf();
                }
            });
        }
    }

    private void audioStop() {
        mediaPlayer.stop(); // 再生終了
        mediaPlayer.reset();  // リセット
        mediaPlayer.release();  // リソースの解放
        mediaPlayer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy()");
        if (mediaPlayer != null) {
            Log.d(TAG, "The end of audio");
            audioStop();
        }
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO for communication return IBinder implementation
        // bindService() で呼び出した場合
        // onStartCommand() ではなく onBind() が callback される
        return null;
    }
}
