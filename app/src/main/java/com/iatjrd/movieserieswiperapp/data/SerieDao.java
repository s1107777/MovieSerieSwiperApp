package com.iatjrd.movieserieswiperapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iatjrd.movieserieswiperapp.model.Movie;
import com.iatjrd.movieserieswiperapp.model.Serie;

import java.util.List;

@Dao
public interface SerieDao {
    //CRUD OPERATIONS GO HERE

    @Query("SELECT * FROM serie_table")
    LiveData<List<Serie>> getAllSeries();

    @Query("SELECT * FROM serie_table WHERE seriegenre = 'Action'")
    LiveData<List<Serie>> getGenreActionSerie();

    @Query("SELECT * FROM serie_table WHERE seriegenre = 'Adventure'")
    LiveData<List<Serie>> getGenreAdventureSerie();

    @Query("SELECT * FROM serie_table WHERE seriegenre = 'Comedy'")
    LiveData<List<Serie>> getGenreComedySerie();

    @Query("SELECT * FROM serie_table WHERE seriegenre = 'Crime'")
    LiveData<List<Serie>> getGenreCrimeSerie();

    @Query("SELECT * FROM serie_table WHERE seriegenre = 'Fantasy'")
    LiveData<List<Serie>> getGenreFantasySerie();

    @Query("SELECT * FROM serie_table WHERE seriegenre = 'Thriller'")
    LiveData<List<Serie>> getGenreThrillerSerie();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Serie serie);

    @Query("DELETE FROM serie_table")
    void deleteAll();

    @Delete
    void delete(Serie serie);
}
