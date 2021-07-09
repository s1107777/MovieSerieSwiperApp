package com.iatjrd.movieserieswiperapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.iatjrd.movieserieswiperapp.model.Movie;
import com.iatjrd.movieserieswiperapp.util.MovieRoomDatabase;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovies;
    private LiveData<List<Movie>> genreAction;
    private LiveData<List<Movie>> genreAdventure;
    private LiveData<List<Movie>> genreComedy;
    private LiveData<List<Movie>> genreCrime;
    private LiveData<List<Movie>> genreFantasy;
    private LiveData<List<Movie>> genreThriller;

    public MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        movieDao = db.movieDao();

        allMovies = movieDao.getAllMovies();
        genreAction = movieDao.getGenreAction();
        genreAdventure = movieDao.getGenreAdventure();
        genreComedy = movieDao.getGenreComedy();
        genreCrime = movieDao.getGenreCrime();
        genreFantasy = movieDao.getGenreFantasy();
        genreThriller = movieDao.getGenreThriller();
    }

    public LiveData<List<Movie>> getAllData(){ return allMovies; }
    public LiveData<List<Movie>> getGenreAction(){ return genreAction; }
    public LiveData<List<Movie>> getGenreAdventure(){ return genreAdventure; }
    public LiveData<List<Movie>> getGenreComedy(){ return genreComedy; }
    public LiveData<List<Movie>> getGenreCrime(){ return genreCrime; }
    public LiveData<List<Movie>> getGenreFantasy(){ return genreFantasy; }
    public LiveData<List<Movie>> getGenreThriller(){ return genreThriller; }

    public void insert(Movie movie){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            movieDao.insert(movie);
        });
    }

    public void deleteAll(Movie movie){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> movieDao.delete(movie));
    }
}
