package com.iatjrd.movieserieswiperapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "movie_table")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "moviename")
    private String moviename;

    @ColumnInfo(name = "moviegenre")
    private String moviegenre;

    @ColumnInfo(name = "movieurl")
    private String movieurl;

    @ColumnInfo(name = "moviedescription")
    private String moviedescription;

    public Movie(@NonNull String moviename, String moviegenre, String moviedescription, String movieurl) {
        this.moviename = moviename;
        this.moviegenre = moviegenre;
        this.movieurl = movieurl;
        this.moviedescription = moviedescription;
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

    public String getMovieurl() {
        return movieurl;
    }

    public void setMovieurl(String movieurl) {
        this.movieurl = movieurl;
    }

    public String getMoviedescription() {
        return moviedescription;
    }

    public void setMoviedescription(String moviedescription) {
        this.moviedescription = moviedescription;
    }
}