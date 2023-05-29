package com.google.itubeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import cn.jzvd.JZVideoPlayerStandard;

public class PlayActivity extends AppCompatActivity {
    public String[] playlist;

//    private VideoView mVideoView;
        JZVideoPlayerStandard mVideoView;
    private MediaController mMediaController;
    private String mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));  //Set status bar color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);  //Implement the status bar icon and text color as dark
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mUri = getIntent().getStringExtra("URL");
        mMediaController = new MediaController(this);
        mVideoView = (JZVideoPlayerStandard) findViewById(R.id.video);
        mVideoView.setUp(mUri, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");

//        mVideoView = findViewById(R.id.video);  // Get an instance of the VideoView component
//        mVideoView.setVideoURI(Uri.parse(mUri));  // Set the URI (Uniform Resource Identifier) of the video, loading it from the network
//        mMediaController.setMediaPlayer(mVideoView);  // Set the media player of the media controller to be the VideoView
//        mVideoView.setMediaController(mMediaController);  // Set the media controller to the VideoView
//        mVideoView.start();  // Start playing the video
    }
}