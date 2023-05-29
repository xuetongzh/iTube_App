package com.google.itubeapp.bean;

import androidx.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

public class UserBean extends LitePalSupport {
    private String fullname;
    private String username;
    private String passsword;

    @NonNull
    @Override
    public String toString() {
        return "UserBean{" +
                "fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", passsword='" + passsword + '\'' +
                '}';
    }

    public UserBean(String fullname, String username, String passsword) {
        this.fullname = fullname;
        this.username = username;
        this.passsword = passsword;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
}
