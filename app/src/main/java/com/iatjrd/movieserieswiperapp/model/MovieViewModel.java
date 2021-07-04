package com.iatjrd.movieserieswiperapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.iatjrd.movieserieswiperapp.data.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    public static MovieRepository repository;
    public final LiveData<List<Movie>> allMovies;
    private LiveData<List<Movie>> genreAction;
    private LiveData<List<Movie>> genreAdventure;
    private LiveData<List<Movie>> genreComedy;
    private LiveData<List<Movie>> genreCrime;
    private LiveData<List<Movie>> genreFantasy;
    private LiveData<List<Movie>> genreThriller;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAllData();
        genreAction = repository.getGenreAction();
        genreAdventure = repository.getGenreAdventure();
        genreComedy = repository.getGenreComedy();
        genreCrime = repository.getGenreCrime();
        genreFantasy = repository.getGenreFantasy();
        genreThriller = repository.getGenreThriller();
    }

    public LiveData<List<Movie>> getAllMovies(){ return allMovies; }
    public LiveData<List<Movie>> getGenreAction(){ return genreAction; }
    public LiveData<List<Movie>> getGenreAdventure(){ return genreAdventure; }
    public LiveData<List<Movie>> getGenreComedy(){ return genreComedy; }
    public LiveData<List<Movie>> getGenreCrime(){ return genreCrime; }
    public LiveData<List<Movie>> getGenreFantasy(){ return genreFantasy; }
    public LiveData<List<Movie>> getGenreThriller(){ return genreThriller; }

    public static void insert(Movie movie) { repository.insert(movie);}

}
