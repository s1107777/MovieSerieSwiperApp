package com.iatjrd.movieserieswiperapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "moviename")
    private String moviename;

    @ColumnInfo(name = "moviegenre")
    private String moviegenre;


    public Movie(@NonNull String moviename, String moviegenre) {
        this.moviename = moviename;
        this.moviegenre = moviegenre;
    }

    public int getId() {
        return id;
    }

    public String getMoviename() {
        return moviename;
    }

    public String getMoviegenre() {
        return moviegenre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public void setMoviegenre(String moviegenre) {
        this.moviegenre = moviegenre;
    }
}