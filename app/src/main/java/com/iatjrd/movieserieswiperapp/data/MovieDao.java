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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie movie);

    @Query("DELETE FROM movie_table")
    void deleteAll();
}

