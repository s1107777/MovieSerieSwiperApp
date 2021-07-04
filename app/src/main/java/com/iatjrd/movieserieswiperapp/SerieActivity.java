package com.iatjrd.movieserieswiperapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.iatjrd.movieserieswiperapp.adapter.SavedItemStackAdapter;
import com.iatjrd.movieserieswiperapp.adapter.SerieStackAdapter;

import com.iatjrd.movieserieswiperapp.data.SerieDao;

import com.iatjrd.movieserieswiperapp.model.SavedItem;
import com.iatjrd.movieserieswiperapp.model.SavedItemViewModel;
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

import java.util.ArrayList;
import java.util.List;

public class SerieActivity extends AppCompatActivity {

    public SerieViewModel serieViewModel;
    private CardStackLayoutManager manager;
    private SerieStackAdapter adapter;
    private SavedItemStackAdapter adapterSavedItem;
    public List<Serie> series = new ArrayList<>();
    public List<SavedItem> savedItems = new ArrayList<>();
    public String serieImage = "";
    public String serieName = "test";
    public String serieGenre = "test";
    public String serieDescription = "test";
    public String serieSeasons = "test";
    public SavedItemViewModel savedItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        CardStackView cardStackView = findViewById(R.id.serie_stack_view);

        serieViewModel = new ViewModelProvider.AndroidViewModelFactory(SerieActivity.this.getApplication())
                .create(SerieViewModel.class);

        serieViewModel.getAllSeries().observe(this, new Observer<List<Serie>>() {
            @Override
            public void onChanged(List<Serie> series) {
                adapter.setSeries(series);
                //textView.setText(builder.toString());

            }
        });

        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardSwiped(Direction direction) {
                if (direction == Direction.Right) {
                    Toast.makeText(SerieActivity.this, "Direction Right", Toast.LENGTH_SHORT).show();
                    //moviesSwipedRight.add(addedMoviesForSwipe.toString());
                    //Log.d("saved list", moviesSwipedRight.toString());
                    //addedMoviesForSwipe.remove(Index);
                    //addItemToList(movieName, movieGenre);
                    savedItems(serieName, serieGenre, serieDescription, serieSeasons);

                }
                if (direction == Direction.Top) {
                    Toast.makeText(SerieActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left) {
                    Toast.makeText(SerieActivity.this, "Direction Left", Toast.LENGTH_SHORT).show();
                    //addedMoviesForSwipe.remove(Index);
                    //addedMoviesForSwipe.remove(Index);
                    //.d("deleted movies", addedMoviesForSwipe.toString());
                }
                if (direction == Direction.Bottom) {
                    Toast.makeText(SerieActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5) {
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
                TextView tv = view.findViewById(R.id.serie_name);
                TextView genre = view.findViewById(R.id.serie_genre);
                TextView description = view.findViewById(R.id.serie_description);
                TextView seasons = view.findViewById(R.id.serie_seasons);
                //ImageView movieImageUrl = view.findViewById(R.id.movie_image);
                //View movieNameView = view.findViewById(R.id.movie_name);
                //movieName = String.valueOf(tv.getText());
                //movieGenre = String.valueOf(genreMovie.getText());
                //movieImage = String.valueOf(movieImageUrl.get);

                /*serieName = String.valueOf(tv.getText());
                serieGenre = String.valueOf(genre.getText());
                serieDescription = String.valueOf(description.getText());
                serieSeasons = String.valueOf(seasons.getText());*/

                Log.d("SeriesAdded", serieName + serieGenre +  serieDescription + serieSeasons);
                Log.d("MainActivity", "onCardAppeared: " + position + ", movie name: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.serie_name);
                Log.d("MainActivity", "onCardAppeared: " + position + ", movie name: " + tv.getText());
            }
        });

        adapter = new SerieStackAdapter(addList());
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


    private List<Serie> addList() {
        String Serieurl = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/series";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Serieurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                Serie serie = new Serie(jsonObject.getString("serieName"),
                                        jsonObject.getString("genre"),
                                        jsonObject.getString("description"),
                                        jsonObject.getString("seasons"),
                                        jsonObject.getString("serieImageUrl"));
                                SerieViewModel.insert(serie);
                                Log.d("SerieAdded", "the movie method added has been called");
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
        return series;
    }

    public void savedItems(String serieName, String serieGenre, String serieDescription, String serieSeasons){
        SavedItem savedItem = new SavedItem(serieName, serieGenre, serieDescription, serieSeasons);
        SavedItemViewModel.insert(savedItem);
    }
}
