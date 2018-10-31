package com.journaldev.videoview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    ArrayList<String> arrayList;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        arrayList = new ArrayList<>(Arrays.asList(
                "android.resource://"+getPackageName()+"/"+R.raw.a,
                "android.resource://"+getPackageName()+"/"+R.raw.b,
                "android.resource://"+getPackageName()+"/"+R.raw.c,
                "android.resource://"+getPackageName()+"/"+R.raw.d,
                "android.resource://"+getPackageName()+"/"+R.raw.e,
                "android.resource://"+getPackageName()+"/"+R.raw.f,
                "android.resource://"+getPackageName()+"/"+R.raw.g,
                "android.resource://"+getPackageName()+"/"+R.raw.h,
                "android.resource://"+getPackageName()+"/"+R.raw.i,
                "android.resource://"+getPackageName()+"/"+R.raw.l,
                "android.resource://"+getPackageName()+"/"+R.raw.q));

        final MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView);



        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(Uri.parse(arrayList.get(index)));
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        videoView.setMediaController(mediacontroller);
                        mediacontroller.setAnchorView(videoView);

                    }
                });
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                if (index++ == arrayList.size()) {
                    index = 0;
                    mp.release();
                    Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                } else {
                    videoView.setVideoURI(Uri.parse(arrayList.get(index)));
                    videoView.start();
                }


            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("API123", "What " + what + " extra " + extra);
                return false;
            }
        });
    }
}
