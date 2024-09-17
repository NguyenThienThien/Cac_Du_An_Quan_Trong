package com.longthph30891.note_mvp.model;

import android.text.TextUtils;
import android.util.Patterns;

public class User {
    private String username,password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    public boolean isValidEmail(){
        return !TextUtils.isEmpty(username);
    }
    public boolean isValidPassword(){
        return !TextUtils.isEmpty(password) && password.length() >= 8;
    }
}
