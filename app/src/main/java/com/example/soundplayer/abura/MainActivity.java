package com.example.soundplayer.abura;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MediaPlayer player;
    private Button playBtn,stopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button playBtn = (Button) findViewById(R.id.play);
        Button stopBtn = (Button) findViewById(R.id.stop);


        playBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.play:
                play();
                break;
            case R.id.stop:
                stop();
                break;
        }
    }

    public void play(){
        if(player == null){
            player = MediaPlayer.create(this,R.raw.azan);
        }
        player.start();
    }

    public void stop(){
        if(player != null){
            player.release();
            player = null;
        }
    }
}