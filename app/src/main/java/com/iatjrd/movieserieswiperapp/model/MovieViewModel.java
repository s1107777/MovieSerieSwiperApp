package com.iatjrd.movieserieswiperapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.iatjrd.movieserieswiperapp.data.MovieRepository;
import com.iatjrd.movieserieswiperapp.data.SavedItemRepository;
import com.iatjrd.movieserieswiperapp.data.SerieRepository;
import com.iatjrd.movieserieswiperapp.data.UserRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    public static MovieRepository repository;
    public final LiveData<List<Movie>> allMovies;

    public static SerieRepository serieRepository;
    public final LiveData<List<Serie>> allSeries;

    public static SavedItemRepository savedItemRepository;
    public final LiveData<List<SavedItem>> allSavedItems;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        savedItemRepository = new SavedItemRepository(application);
        serieRepository = new SerieRepository(application);

        allMovies = repository.getAllData();
        allSavedItems = savedItemRepository.getAllData();
        allSeries = serieRepository.getAllData();

        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();

        genreAction = repository.getGenreAction();
        genreAdventure = repository.getGenreAdventure();
        genreComedy = repository.getGenreComedy();
        genreCrime = repository.getGenreCrime();
        genreFantasy = repository.getGenreFantasy();
        genreThriller = repository.getGenreThriller();
    }


    public LiveData<List<Movie>> getAllMovies(){ return allMovies; }
    public static void insert(Movie movie) { repository.insert(movie);}

    public LiveData<List<SavedItem>> getAllSavedItems(){ return allSavedItems; }
    public static void insertSaveItem(SavedItem savedItem) { savedItemRepository.insert(savedItem);}

    public LiveData<List<Serie>> getAllSeries(){ return allSeries; }
    public static void insertSerie(Serie serie) { serieRepository.insert(serie);}

    public LiveData<List<User>> getAllUsers() { return allUsers; }
    public static void insertUser(User user){userRepository.insert(user);}

    public LiveData<List<Movie>> getGenreAction(){ return genreAction; }
    public LiveData<List<Movie>> getGenreAdventure(){ return genreAdventure; }
    public LiveData<List<Movie>> getGenreComedy(){ return genreComedy; }
    public LiveData<List<Movie>> getGenreCrime(){ return genreCrime; }
    public LiveData<List<Movie>> getGenreFantasy(){ return genreFantasy; }
    public LiveData<List<Movie>> getGenreThriller(){ return genreThriller; }

}
