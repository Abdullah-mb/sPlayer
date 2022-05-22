package com.example.soundplayer.abura;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MediaPlayerService extends Service {

    MediaPlayer player;

    public MediaPlayerService(){

    }


    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String trackName = intent.getStringExtra("TRACK_KEY");
        int track = getResources().getIdentifier(trackName,"raw",getPackageName());

        if(player == null){
            player = MediaPlayer.create(getBaseContext(),track);
        }
        player.start();

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSelf();
//                stopForeground(true);
            }
        });

        startForeground(122, getNotificationDetails());
        return START_STICKY;
    }


    public Notification getNotificationDetails(){

        String channelID = "media playerID";
        NotificationChannel playerChannel = new NotificationChannel(
                channelID,
                channelID,
                NotificationManager.IMPORTANCE_HIGH);

        getSystemService(NotificationManager.class).createNotificationChannel(playerChannel);
        Notification.Builder azanNotification = new Notification.Builder(this, channelID)
                .setContentText("AzanPLayer is running")
                .setContentTitle("AzanPlayer enabled")
                .setSmallIcon(R.drawable.ic_launcher_background);

        return azanNotification.build();
    }


    @Override
    public void onDestroy() {
        if(player != null) {
            player.release();
            player = null;
        }
        stopSelf();
    }

}