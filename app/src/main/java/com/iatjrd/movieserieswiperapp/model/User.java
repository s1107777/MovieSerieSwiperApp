package com.iatjrd.movieserieswiperapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "user_id")
    private String user_id;

    @ColumnInfo(name = "user_name")
    private String user_name;

    @ColumnInfo(name = "token")
    private String token;

    public User(@NonNull String user_id, String user_name, String token){
        this.user_id = user_id;
        this.user_name = user_name;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getToken() {
        return token;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
