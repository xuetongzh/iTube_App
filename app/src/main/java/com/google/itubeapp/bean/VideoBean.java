package com.google.itubeapp.bean;

import androidx.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

public class VideoBean extends LitePalSupport {
    private String username;
    private String videoUrl;

    @NonNull
    @Override
    public String toString() {
        return "VideoBean{" +
                "username='" + username + '\'' +
                "videoUrl='" + videoUrl + '\'' +
                '}';
    }

    public VideoBean(String username, String videoUrl) {
        this.username = username;
        this.videoUrl = videoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
