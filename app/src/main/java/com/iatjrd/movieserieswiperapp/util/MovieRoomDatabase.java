package com.iatjrd.movieserieswiperapp.util;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.iatjrd.movieserieswiperapp.data.MovieDao;
import com.iatjrd.movieserieswiperapp.model.Movie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    private static final int NUMBER_OF_THREADS = 4;
    public String Movieurl = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/movies";

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
                movieDao.deleteAll();

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
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}

