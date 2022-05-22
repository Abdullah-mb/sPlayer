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

public class MediaPlayerService extends Service {

    MediaPlayer player;

    public MediaPlayerService(){

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String trackName = intent.getStringExtra("TRACK_KEY");
        startForeground(122, getNotificationDetails());
        if(player == null){
            try {
                Toast.makeText(getBaseContext(),"hallo "+trackName,Toast.LENGTH_LONG).show();
                player.setDataSource(trackName);
                player.prepare();
                player.start();
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "Cannot Play Track!", Toast.LENGTH_SHORT).show();
            }
        }
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSelf();
//                stopForeground(true);
            }
        });
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        if(player != null) {
            player.release();
            player = null;
        }
        stopSelf();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
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

}