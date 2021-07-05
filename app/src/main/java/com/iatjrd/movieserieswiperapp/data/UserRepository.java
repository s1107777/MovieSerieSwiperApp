package com.iatjrd.movieserieswiperapp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.iatjrd.movieserieswiperapp.model.User;
import com.iatjrd.movieserieswiperapp.util.MovieRoomDatabase;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application){
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() { return allUsers; }

    public void insert (User user){
        MovieRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }
    public void delete(User user){
        MovieRoomDatabase.databaseWriteExecutor.execute( () -> userDao.deleteAll());
    }
}
