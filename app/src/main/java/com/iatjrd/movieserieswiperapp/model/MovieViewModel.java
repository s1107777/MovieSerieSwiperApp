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

    public static UserRepository userRepository;
    public final LiveData<List<User>> allUsers;

    private LiveData<List<Movie>> genreAction;
    private LiveData<List<Movie>> genreAdventure;
    private LiveData<List<Movie>> genreComedy;
    private LiveData<List<Movie>> genreCrime;
    private LiveData<List<Movie>> genreFantasy;
    private LiveData<List<Movie>> genreThriller;

    private LiveData<List<Serie>> genreActionS;
    private LiveData<List<Serie>> genreAdventureS;
    private LiveData<List<Serie>> genreComedyS;
    private LiveData<List<Serie>> genreCrimeS;
    private LiveData<List<Serie>> genreFantasyS;
    private LiveData<List<Serie>> genreThrillerS;

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

        genreActionS = serieRepository.getGenreActionSerie();
        genreAdventureS = serieRepository.getGenreAdventureSerie();
        genreComedyS = serieRepository.getGenreComedySerie();
        genreCrimeS = serieRepository.getGenreCrimeSerie();
        genreFantasyS = serieRepository.getGenreFantasySerie();
        genreThrillerS = serieRepository.getGenreThrillerSerie();
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

    public LiveData<List<Serie>> getGenreActionSerie(){ return genreActionS; }
    public LiveData<List<Serie>> getGenreAdventureSerie(){ return genreAdventureS; }
    public LiveData<List<Serie>> getGenreComedySerie(){ return genreComedyS; }
    public LiveData<List<Serie>> getGenreCrimeSerie(){ return genreCrimeS; }
    public LiveData<List<Serie>> getGenreFantasySerie(){ return genreFantasyS; }
    public LiveData<List<Serie>> getGenreThrillerSerie(){ return genreThrillerS; }

}
