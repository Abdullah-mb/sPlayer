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

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button playBtn,stopBtn;
    ListView azanListView;

    List<String> azanList;
    String track;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button playBtn = (Button) findViewById(R.id.play);
        Button stopBtn = (Button) findViewById(R.id.stop);
        ListView azanListView = (ListView) findViewById(R.id.ListView);

        azanList = new ArrayList<String>();
        addToList();

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,azanList);
        azanListView.setAdapter(arrayAdapter);

        azanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                track = (String) azanList.get(position);
            }
        } );


        playBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

    }


    private void addToList() {
        Field[] fields=R.raw.class.getFields();
        for(int count=0; count < fields.length; count++){
            azanList.add(fields[count].getName());
        }
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
        Intent intent = new Intent(getBaseContext(), MediaPlayerService.class);
        intent.putExtra("TRACK_KEY",track);
        startForegroundService(intent);
    }


    public void stop(){
        Intent intent = new Intent(getBaseContext(),MediaPlayerService.class);
        stopService(intent);
        }
    }