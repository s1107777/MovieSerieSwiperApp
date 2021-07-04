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

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAllData();
    }

    public LiveData<List<Movie>> getAllMovies(){ return allMovies; }
    public static void insert(Movie movie) { repository.insert(movie);}
}
