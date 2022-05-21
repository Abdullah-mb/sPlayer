package com.example.soundplayer.abura;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

    @RequiresApi(api = Build.VERSION_CODES.O)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void play(){
        Intent intent = new Intent(getBaseContext(),BackgroundService.class);
//        intent.putExtra("songName",R.raw.azan);
        startForegroundService(intent);

    }

    public void stop(){
            Intent intent = new Intent(getBaseContext(),BackgroundService.class);
            stopService(intent);
        }
    }