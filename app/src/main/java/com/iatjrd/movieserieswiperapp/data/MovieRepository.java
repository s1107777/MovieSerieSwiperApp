package com.iatjrd.movieserieswiperapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.iatjrd.movieserieswiperapp.model.Movie;
import com.iatjrd.movieserieswiperapp.util.MovieRoomDatabase;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;

    public MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        movieDao = db.movieDao();

        allMovies = movieDao.getAllMovies();
    }

    public LiveData<List<Movie>> getAllData(){ return allMovies; }
    public void insert(Movie movie){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            movieDao.insert(movie);
        });
    }
}
