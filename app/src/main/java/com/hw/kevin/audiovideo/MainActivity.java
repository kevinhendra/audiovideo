package com.hw.kevin.audiovideo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay;
    private Button btnPause;
    private Button btnStop;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        mp = new MediaPlayer();

        btnPlay = (Button) findViewById(R.id.tombolPlay);
        btnPause = (Button) findViewById(R.id.tombolPause);
        btnStop = (Button) findViewById(R.id.tombolStop);

        stateAwal();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
                btnPlay.setEnabled(false);
                btnPause.setEnabled(true);
                btnStop.setEnabled(true);
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });
    }

    private void stateAwal() {
        btnPlay.setEnabled(true);
        btnPause.setEnabled(false);
        btnStop.setEnabled(false);
    }

    private void play(){
        mp = MediaPlayer.create(this,R.raw.anxiety);
        try{
            mp.prepare();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stateAwal();
            }
        });
    }

    public void pause() {
        if (mp.isPlaying()) {
            if (mp != null) {
                mp.pause();
            }
        } else {
            if (mp != null) {
                mp.start();
            }
        }
    }
    public void stop(){
        mp.stop();

        try{
            mp.prepare();
            mp.seekTo(0);
        } catch (Throwable t){
            t.getStackTrace();
        }
        stateAwal();
    }
}
