package com.iatjrd.movieserieswiperapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.iatjrd.movieserieswiperapp.data.SavedItemRepository;

import java.util.List;

public class SavedItemViewModel extends AndroidViewModel {
    public static SavedItemRepository repository;
    public final LiveData<List<SavedItem>> allSavedItems;

    public SavedItemViewModel(@NonNull Application application) {
        super(application);
        repository = new SavedItemRepository(application);
        allSavedItems = repository.getAllData();
    }

    public LiveData<List<SavedItem>> getAllSavedItems(){ return allSavedItems; }
    public static void insert(SavedItem savedItem) { repository.insert(savedItem);}
}
