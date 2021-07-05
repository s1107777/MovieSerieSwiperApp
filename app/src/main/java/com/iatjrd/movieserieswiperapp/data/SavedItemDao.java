package com.iatjrd.movieserieswiperapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iatjrd.movieserieswiperapp.model.SavedItem;

import java.util.List;

@Dao
public interface SavedItemDao {

    //CRUD OPERATIONS GO HERE

    @Query("SELECT * FROM saveditem_table")
    LiveData<List<SavedItem>> getAllSavedItems();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SavedItem savedItem);

    @Query("DELETE FROM saveditem_table")
    void deleteAll();

    @Delete
    void delete(SavedItem savedItem);
}
