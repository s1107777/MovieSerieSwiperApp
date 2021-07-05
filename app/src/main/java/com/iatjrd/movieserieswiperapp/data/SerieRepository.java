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

    public SerieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        serieDao = db.serieDao();

        allSeries = serieDao.getAllSeries();
    }

    public LiveData<List<Serie>> getAllData(){ return allSeries; }
    public void insert(Serie serie){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            serieDao.insert(serie);
        });
    }

    public void delete(Serie serie){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> serieDao.deleteAll());
    }
}
