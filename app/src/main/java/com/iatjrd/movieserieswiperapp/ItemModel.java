package com.iatjrd.movieserieswiperapp;

public class ItemModel {
    private String movieId;
    private String movieName;
    private String genre;
    private String movieImage;

    public ItemModel(){

    }

    public ItemModel(String movieId, String movieImage, String movieName, String genre){
        this.movieId = movieId;
        this.movieImage = movieImage;
        this.movieName = movieName;
        this.genre = genre;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getGenre() {
        return genre;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
