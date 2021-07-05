package com.iatjrd.movieserieswiperapp.util;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.iatjrd.movieserieswiperapp.data.MovieDao;
import com.iatjrd.movieserieswiperapp.data.UserDao;
import com.iatjrd.movieserieswiperapp.data.SavedItemDao;
import com.iatjrd.movieserieswiperapp.data.SerieDao;
import com.iatjrd.movieserieswiperapp.model.Movie;
import com.iatjrd.movieserieswiperapp.model.User;
import com.iatjrd.movieserieswiperapp.model.SavedItem;
import com.iatjrd.movieserieswiperapp.model.Serie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Movie.class, User.class, Serie.class, SavedItem.class}, version = 75, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    //Importing Dao
    public abstract MovieDao movieDao();
    public abstract UserDao userDao();
    public abstract SerieDao serieDao();
    public abstract SavedItemDao savedItemDao();

    private static final int NUMBER_OF_THREADS = 4;


    private static volatile MovieRoomDatabase INSTANCE;

    //Executor service helpt ons om dingen te draain in de backthread
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final RoomDatabase.Callback sRoomDatabaseCallBack = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                MovieDao movieDao = INSTANCE.movieDao();
                SerieDao serieDao = INSTANCE.serieDao();
                SavedItemDao savedItemDao = INSTANCE.savedItemDao();

                movieDao.deleteAll();
                serieDao.deleteAll();
                savedItemDao.deleteAll();

                UserDao userDao = INSTANCE.userDao();
                userDao.deleteAll();

                /*Movie movie = new Movie("James Bond", "Action");
                movieDao.insert(movie);

                movie = new Movie("Spiderman", "Action");
                movieDao.insert(movie);

                movie = new Movie("Hulk", "Action");
                movieDao.insert(movie);*/
            });
        }
    };



    public static MovieRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null) {
            synchronized (MovieRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieRoomDatabase.class,
                            "movie_database")
                            .addCallback(sRoomDatabaseCallBack)
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}

