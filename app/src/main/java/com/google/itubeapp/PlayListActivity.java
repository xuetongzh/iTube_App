package com.google.itubeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.itubeapp.bean.VideoBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity {
    public String username;
    public List<String> list;
    public ListView listView;
    private List<VideoBean> videoBeans = new ArrayList<>();

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));  //Set status bar color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);  //Implement the status bar icon and text color as dark
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        listView = findViewById(R.id.lv_playlist);  // Get the instance of the ListView component
        username = getIntent().getStringExtra("username");  // Get the username information passed from the previous Activity

        // Retrieve video data from the database that matches the username
        videoBeans = LitePal.where("username = ?", username).find(VideoBean.class);
        list = new ArrayList<>();
        for (VideoBean videoBean : videoBeans) {
            list.add(videoBean.getVideoUrl());  // Add the video URL to the list
        }

        // Create an ArrayAdapter as the adapter for the ListView, use a simple list item layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(PlayListActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);  // Set adapter for the ListView

        // Set the click event for the ListView, when a list item is clicked, it opens the PlayActivity and passes the corresponding URL
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PlayListActivity.this, PlayActivity.class);
                intent.putExtra("URL", list.get(i));
                startActivity(intent);
            }
        });


    }
}