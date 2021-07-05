package com.iatjrd.movieserieswiperapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.iatjrd.movieserieswiperapp.data.SerieRepository;

import java.util.List;
import java.util.Objects;

public class SerieViewModel extends AndroidViewModel {
    public static SerieRepository repository;
    public final LiveData<List<Serie>> allSeries;

    public SerieViewModel(@NonNull Application application) {
        super(application);
        repository = new SerieRepository(application);
        allSeries = repository.getAllData();
    }

    public LiveData<List<Serie>> getAllSeries(){ return allSeries; }
    public static void insert(Serie serie) { repository.insert(serie);}
}
