package com.iatjrd.movieserieswiperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iatjrd.movieserieswiperapp.model.Movie;
import com.iatjrd.movieserieswiperapp.model.MovieViewModel;

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
    private TextView textView;
    public String Movieurl = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        profileButton = findViewById(R.id.profileButton);

        movieViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(MovieViewModel.class);
        getCheckboxValues();
        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                StringBuilder builder = new StringBuilder();

//                Log.d("isValid", String.valueOf(isValid));
                for (Movie movie : movies) {
                    //builder.append(" - " + movie.getMoviename() + " " + movie.getMoviegenre());
                    Log.d("TAG", "onCreate: " + movie.getMoviename());
                }
                //textView.setText(builder.toString());

            }
        });

        String Movieurl = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/movies";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Movieurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);
                                Movie movie = new Movie(jsonObject.getString("movieName"), jsonObject.getString("genre"));
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


        profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });
    }

    public void getCheckboxValues(){
        SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
        boolean action = sharedPreferences.getBoolean("checkAction", false);
        boolean adventure = sharedPreferences.getBoolean("checkAdventure", false);
        boolean comedy = sharedPreferences.getBoolean("checkComedy", false);
        boolean crime = sharedPreferences.getBoolean("checkCrime", false);
        boolean fanatasy = sharedPreferences.getBoolean("checkFantasy", false);
        boolean thriller = sharedPreferences.getBoolean("checkThriller", false);

        movieViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if(action){
                    for (Movie movie : movies) {
                        
                    }
                    Log.d("action", "ACTION");
                }
                if(adventure){
                    Log.d("adventure", "adventure");
                }
                if(comedy){
                    Log.d("comedy", "comedy");
                }
                if(crime){
                    Log.d("crime", "crime");
                }
                if(fanatasy){
                    Log.d("fanatasy", "fanatasy");
                }
                if(thriller){
                    Log.d("thriller", "thriller");
                }
            }
        });


    }
}