package com.iatjrd.movieserieswiperapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iatjrd.movieserieswiperapp.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert (User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Delete
    void delete(User user);
}
