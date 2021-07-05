package com.iatjrd.movieserieswiperapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.iatjrd.movieserieswiperapp.model.Movie;
import com.iatjrd.movieserieswiperapp.model.Serie;
import com.iatjrd.movieserieswiperapp.util.MovieRoomDatabase;

import java.util.List;

public class SerieRepository {

    private SerieDao serieDao;
    private LiveData<List<Serie>> allSeries;

    private LiveData<List<Serie>> genreActionS;
    private LiveData<List<Serie>> genreAdventureS;
    private LiveData<List<Serie>> genreComedyS;
    private LiveData<List<Serie>> genreCrimeS;
    private LiveData<List<Serie>> genreFantasyS;
    private LiveData<List<Serie>> genreThrillerS;

    public SerieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        serieDao = db.serieDao();

        allSeries = serieDao.getAllSeries();
        genreActionS = serieDao.getGenreActionSerie();
        genreAdventureS = serieDao.getGenreAdventureSerie();
        genreComedyS = serieDao.getGenreComedySerie();
        genreCrimeS = serieDao.getGenreCrimeSerie();
        genreFantasyS = serieDao.getGenreFantasySerie();
        genreThrillerS = serieDao.getGenreThrillerSerie();
    }

    public LiveData<List<Serie>> getAllData(){ return allSeries; }
    public LiveData<List<Serie>> getGenreActionSerie(){ return genreActionS; }
    public LiveData<List<Serie>> getGenreAdventureSerie(){ return genreAdventureS; }
    public LiveData<List<Serie>> getGenreComedySerie(){ return genreComedyS; }
    public LiveData<List<Serie>> getGenreCrimeSerie(){ return genreCrimeS; }
    public LiveData<List<Serie>> getGenreFantasySerie(){ return genreFantasyS; }
    public LiveData<List<Serie>> getGenreThrillerSerie(){ return genreThrillerS; }
    public void insert(Serie serie){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            serieDao.insert(serie);
        });
    }

    public void delete(Serie serie){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> serieDao.deleteAll());
    }
}
