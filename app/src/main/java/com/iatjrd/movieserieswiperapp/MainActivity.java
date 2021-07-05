package com.iatjrd.movieserieswiperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iatjrd.movieserieswiperapp.adapter.CardStackAdapter;
import com.iatjrd.movieserieswiperapp.adapter.CardStackCallback;
import com.iatjrd.movieserieswiperapp.adapter.SerieStackAdapter;
import com.iatjrd.movieserieswiperapp.data.MovieDao;
import com.iatjrd.movieserieswiperapp.model.Movie;
import com.iatjrd.movieserieswiperapp.model.MovieViewModel;
import com.iatjrd.movieserieswiperapp.model.Serie;
import com.iatjrd.movieserieswiperapp.model.SerieViewModel;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageButton profileButton;
    public MovieViewModel movieViewModel;
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private SerieStackAdapter serieAdapter;
    public MovieDao movieDao;
    public List<Movie> movies = new ArrayList<>();
    public String Movieurl = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardStackView cardStackView = findViewById(R.id.card_stack_view);

        profileButton = findViewById(R.id.profileButton);

        movieViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(MovieViewModel.class);
        getCheckboxValues();

        profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardSwiped(Direction direction) {
                if (direction == Direction.Right) {
                    Toast.makeText(MainActivity.this, "Direction Right", Toast.LENGTH_SHORT).show();
                    //moviesSwipedRight.add(addedMoviesForSwipe.toString());
                    //Log.d("saved list", moviesSwipedRight.toString());
                    //addedMoviesForSwipe.remove(Index);
                    //addItemToList(movieName, movieGenre);
                }
                if (direction == Direction.Top) {
                    Toast.makeText(MainActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left) {
                    Toast.makeText(MainActivity.this, "Direction Left", Toast.LENGTH_SHORT).show();
                    //addedMoviesForSwipe.remove(Index);
                    //addedMoviesForSwipe.remove(Index);
                    //.d("deleted movies", addedMoviesForSwipe.toString());
                }
                if (direction == Direction.Bottom) {
                    Toast.makeText(MainActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5) {
                    paginate();
                }
            }

            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d("MainActivity", "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }


            @Override
            public void onCardRewound() {
                Log.d("MainActivity", "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d("MainActivity", "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.movie_name);
                TextView genreMovie = view.findViewById(R.id.movie_genre);
                //ImageView movieImageUrl = view.findViewById(R.id.movie_image);
                //View movieNameView = view.findViewById(R.id.movie_name);
                //movieName = String.valueOf(tv.getText());
                //movieGenre = String.valueOf(genreMovie.getText());
                //movieImage = String.valueOf(movieImageUrl.get);

                Log.d("MainActivity", "onCardAppeared: " + position + ", movie name: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.movie_name);
                Log.d("MainActivity", "onCardAppeared: " + position + ", movie name: " + tv.getText());
            }
        });

        adapter = new CardStackAdapter(addList());
        cardStackView.setAdapter(adapter);
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        cardStackView.setLayoutManager(manager);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void paginate() {
        List<Movie> old = adapter.getMovies();
        List<Movie> baru = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setMovies(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    private List<Movie> addList() {
        String Movieurl = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/movies";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Movieurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                Movie movie = new Movie(jsonObject.getString("movieName"),
                                        jsonObject.getString("genre"),
                                        jsonObject.getString("description"),
                                        jsonObject.getString("movieImageUrl"));
                                MovieViewModel.insert(movie);
                                Log.d("MovieAdded", "the movie method added has been called");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("movie error", error.toString());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        return movies;
    }

    public void getCheckboxValues(){
        SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
        boolean action = sharedPreferences.getBoolean("checkAction", false);
        boolean adventure = sharedPreferences.getBoolean("checkAdventure", false);
        boolean comedy = sharedPreferences.getBoolean("checkComedy", false);
        boolean crime = sharedPreferences.getBoolean("checkCrime", false);
        boolean fanatasy = sharedPreferences.getBoolean("checkFantasy", false);
        boolean thriller = sharedPreferences.getBoolean("checkThriller", false);

        SharedPreferences preferences = getSharedPreferences("radio",MODE_PRIVATE);
        boolean serie = preferences.getBoolean("serie", false);
        boolean movie = preferences.getBoolean("movie", false);

        if(action && movie){
            movieViewModel.getGenreAction().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> movies) {
                    for (Movie movie : movies) {
                        adapter.setMovies(movies);
                    }
                }
            });
        }else if(action && serie){
            movieViewModel.getGenreActionSerie().observe(this, new Observer<List<Serie>>() {
                @Override
                public void onChanged(List<Serie> series) {
                    for(Serie serie : series){
                        serieAdapter.setSeries(series);
                    }
                }
            });
        }
        if(adventure && movie){
            movieViewModel.getGenreAdventure().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> movies) {
                    for (Movie movie : movies) {
                        adapter.setMovies(movies);
                    }
                }
            });
        }
        if(comedy && movie){
            movieViewModel.getGenreComedy().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> movies) {
                    for (Movie movie : movies) {
                        adapter.setMovies(movies);
                    }
                }
            });
        }
        if(crime && movie){
            movieViewModel.getGenreCrime().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> movies) {
                    for (Movie movie : movies) {
                        adapter.setMovies(movies);
                    }
                }
            });
        }
        if(fanatasy && movie){
            movieViewModel.getGenreFantasy().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> movies) {
                    for (Movie movie : movies) {
                        adapter.setMovies(movies);
                    }
                }
            });
        }
        if(thriller && movie){
            movieViewModel.getGenreThriller().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> movies) {
                    for (Movie movie : movies) {
                        adapter.setMovies(movies);
                    }
                }
            });
        }
    }

    private void createNotifiction(){
        String CHANNEL_ID = "channel_name";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("test")
                .setContentText("jaallalalala")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Channel name";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            builder.createNotificationChannel(mChannel);
        }
    }
}