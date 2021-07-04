package com.iatjrd.movieserieswiperapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iatjrd.movieserieswiperapp.model.Movie;
import com.iatjrd.movieserieswiperapp.model.MovieViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {

    Button logoutButton;
    TextView username;
    CheckBox genreAction, genreAdventure, genreComedy, genreCrime, genreFantasy, genreThriller;
    public MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logoutButton = findViewById(R.id.logoutButton);
        username = findViewById(R.id.userName);

        genreAction = findViewById(R.id.genreAction);
        genreAdventure = findViewById(R.id.genreAdventure);
        genreComedy = findViewById(R.id.genreComedy);
        genreCrime = findViewById(R.id.genreCrime);
        genreFantasy = findViewById(R.id.genreFantasy);
        genreThriller = findViewById(R.id.genreThriller);

        movieViewModel = new ViewModelProvider.AndroidViewModelFactory(Profile.this.getApplication())
                .create(MovieViewModel.class);

        getUsername(username);
        chooseGenre();

        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

    public void chooseGenre(){
        genreAction.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(genreAction.isChecked()){
                    movieViewModel.getGenreAction().observe(Profile.this, new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> movies) {
                            for (Movie movie: movies){
                                Log.d("genreAction", movie.getMoviename());
                            }
                        }
                    });
                }
            }
        });
        genreAdventure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(genreAdventure.isChecked()){
                    movieViewModel.getGenreAdventure().observe(Profile.this, new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> movies) {
                            for (Movie movie: movies){
                                Log.d("genreAdventure", movie.getMoviename());
                            }
                        }
                    });
                }
            }
        });
        genreComedy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(genreComedy.isChecked()){
                    movieViewModel.getGenreComedy().observe(Profile.this, new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> movies) {
                            for (Movie movie: movies){
                                Log.d("genreComedy", movie.getMoviename());
                            }
                        }
                    });
                }
            }
        });
        genreCrime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(genreCrime.isChecked()){
                    movieViewModel.getGenreCrime().observe(Profile.this, new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> movies) {
                            for (Movie movie: movies){
                                Log.d("genreCrime", movie.getMoviename());
                            }
                        }
                    });
                }
            }
        });
        genreFantasy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(genreFantasy.isChecked()){
                    movieViewModel.getGenreFantasy().observe(Profile.this, new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> movies) {
                            for (Movie movie: movies){
                                Log.d("genreFantasy", movie.getMoviename());
                            }
                        }
                    });
                }
            }
        });
        genreThriller.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(genreThriller.isChecked()){
                    movieViewModel.getGenreThriller().observe(Profile.this, new Observer<List<Movie>>() {
                        @Override
                        public void onChanged(List<Movie> movies) {
                            for (Movie movie: movies){
                                Log.d("genreThriller", movie.getMoviename());
                            }
                        }
                    });
                }
            }
        });


    }

    public void getUsername(TextView username){
        String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/user-profile";
        Bundle bundle = getIntent().getExtras();
        String token = bundle.getString("token");
        Log.d("ProfileToken", token);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String responseString = response;
                        JSONObject json = null;
                        try {
                            json = new JSONObject(responseString);
                            String name = json.getString("name");
                            username.setText("Hi " + name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("usernameError", error.toString());
                        Toast.makeText(Profile.this, "Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + token);
                Log.d("checkHeader", header.toString());
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void logout(){
        String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/logout";
        Bundle bundle = getIntent().getExtras();
        String token = bundle.getString("token");
        Log.d("ProfileToken", token);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("checkResponse", response);
                        Toast.makeText(Profile.this, "Successvolvoly logged out", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("logoutError", error.toString());
                        Toast.makeText(Profile.this, "Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            public Map<String, String> getHeaders()  {
                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + token);
                Log.d("checkHeader", header.toString());
                return header;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}