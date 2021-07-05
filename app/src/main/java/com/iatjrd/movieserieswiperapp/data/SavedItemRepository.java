package com.iatjrd.movieserieswiperapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.iatjrd.movieserieswiperapp.model.SavedItem;
import com.iatjrd.movieserieswiperapp.model.Serie;
import com.iatjrd.movieserieswiperapp.util.MovieRoomDatabase;

import java.util.List;

public class SavedItemRepository {
    private SavedItemDao savedItemDao;
    private LiveData<List<SavedItem>> allSavedItems;

    public SavedItemRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        savedItemDao = db.savedItemDao();

        allSavedItems = savedItemDao.getAllSavedItems();
    }

    public LiveData<List<SavedItem>> getAllData(){ return allSavedItems; }
    public void insert(SavedItem savedItem){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            savedItemDao.insert(savedItem);
        });
    }

    public void delete(SavedItem savedItem){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> savedItemDao.deleteAll());
    }
}
