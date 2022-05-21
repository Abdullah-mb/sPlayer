package com.example.soundplayer.abura;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button playBtn,stopBtn;
    ListView azanListView;
    List azanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button playBtn = (Button) findViewById(R.id.play);
        Button stopBtn = (Button) findViewById(R.id.stop);
        ListView azanListView = (ListView) findViewById(R.id.ListView);

        azanList = new ArrayList<>();
        addToList();

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,azanList);
        azanListView.setAdapter(arrayAdapter);

        azanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String item = (String) arrayAdapter.getItem(position);
                selectTrack(item);
            }
        } );


        playBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

    }

    private void selectTrack(String item) {
//        MediaPlayerService.class.selectTrack
    }

    private void addToList() {
        Field[] fields=R.raw.class.getFields();
        for(int count=0; count < fields.length; count++){
            azanList.add(fields[count].getName());
        }
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
        Intent intent = new Intent(getBaseContext(), MediaPlayerService.class);
//        intent.putExtra("songName",R.raw.azan);
        startForegroundService(intent);

    }

    public void stop(){
            Intent intent = new Intent(getBaseContext(),MediaPlayerService.class);
            stopService(intent);
        }
    }