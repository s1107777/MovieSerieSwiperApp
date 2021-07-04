package com.iatjrd.movieserieswiperapp.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iatjrd.movieserieswiperapp.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    //CRUD OPERATIONS GO HERE

    @Query("SELECT * FROM movie_table  ORDER BY movieName ASC")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM movie_table WHERE moviegenre = 'Action'")
    LiveData<List<Movie>> getGenreAction();

    @Query("SELECT * FROM movie_table WHERE moviegenre = 'Adventure'")
    LiveData<List<Movie>> getGenreAdventure();

    @Query("SELECT * FROM movie_table WHERE moviegenre = 'Comedy'")
    LiveData<List<Movie>> getGenreComedy();

    @Query("SELECT * FROM movie_table WHERE moviegenre = 'Crime'")
    LiveData<List<Movie>> getGenreCrime();

    @Query("SELECT * FROM movie_table WHERE moviegenre = 'Fantasy'")
    LiveData<List<Movie>> getGenreFantasy();

    @Query("SELECT * FROM movie_table WHERE moviegenre = 'Thriller'")
    LiveData<List<Movie>> getGenreThriller();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie movie);

    @Query("DELETE FROM movie_table")
    void deleteAll();
}

