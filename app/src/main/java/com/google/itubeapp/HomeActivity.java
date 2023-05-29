package com.google.itubeapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.itubeapp.bean.VideoBean;

public class HomeActivity extends AppCompatActivity {

    public EditText edit_url;
    public Button btn_play;
    public Button btn_addToList;
    public Button btn_playList;
    public String username;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));  //Set status bar color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);  //Implement the status bar icon and text color as dark
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Initialize views
        edit_url = findViewById(R.id.edit_url);
        btn_play = findViewById(R.id.btn_play);
        btn_addToList = findViewById(R.id.btn_addToList);
        btn_playList = findViewById(R.id.btn_playList);
        username = getIntent().getStringExtra("username");  // Get the username from the intent

        // Set click listener for the play button
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start the PlayActivity and pass the URL as an extra
                Intent intent = new Intent(HomeActivity.this, PlayActivity.class);
                intent.putExtra("URL", edit_url.getText().toString());
                startActivity(intent);
            }
        });

        // Set click listener for the add to list button
        btn_addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new VideoBean with the username and URL, and save it
                if (new VideoBean(username, edit_url.getText().toString()).save()) {
                    Toast.makeText(HomeActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, "Failed to add", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for the play list button
        btn_playList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to start the PlayListActivity and pass the username as an extra
                Intent i = new Intent(HomeActivity.this, PlayListActivity.class);
                i.putExtra("username", username);
                startActivity(i);
            }
        });

    }
}