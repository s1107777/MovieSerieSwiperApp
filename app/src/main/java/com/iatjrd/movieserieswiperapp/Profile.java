package com.iatjrd.movieserieswiperapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
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
import com.iatjrd.movieserieswiperapp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {

    Button logoutButton, changeButton;
    TextView username;
    CheckBox genreAction, genreAdventure, genreComedy, genreCrime, genreFantasy, genreThriller;
    RadioButton radioButtonMovies, radioButtonSeries;
    RadioGroup radioGroup;

    public MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        logoutButton = findViewById(R.id.logoutButton);
        changeButton = findViewById(R.id.changeButton);
        username = findViewById(R.id.userName);

        genreAction = findViewById(R.id.genreAction);
        genreAdventure = findViewById(R.id.genreAdventure);
        genreComedy = findViewById(R.id.genreComedy);
        genreCrime = findViewById(R.id.genreCrime);
        genreFantasy = findViewById(R.id.genreFantasy);
        genreThriller = findViewById(R.id.genreThriller);

        radioGroup = findViewById(R.id.radiogroup);
        radioButtonSeries = findViewById(R.id.radioButtonSeries);
        radioButtonMovies = findViewById(R.id.radioButtonMovies);

        movieViewModel = new ViewModelProvider.AndroidViewModelFactory(Profile.this.getApplication())
                .create(MovieViewModel.class);

        getUsername(username);
//        checkSerieOrMovie();

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                SharedPreferences preferences = getSharedPreferences("radio", MODE_PRIVATE);
//                SharedPreferences.Editor editorRadio = preferences.edit();
//                if(i == R.id.radioButtonMovies){
//                    Log.d("radioButtonLog", String.valueOf(i));
//
//                    editorRadio.putBoolean("radio", true).apply();
//                }
//                else{
//                    Log.d("radioButtonLog", String.valueOf(i));
//                    editorRadio.putBoolean("radio", false).apply();
//                }
//            }
//        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("radio",MODE_PRIVATE);
                boolean radioMovie = sharedPreferences.getBoolean("radioMovie", false);
                boolean radioSerie = sharedPreferences.getBoolean("radioSerie", false);
                if(radioMovie){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                if(radioSerie){
                    startActivity(new Intent(getApplicationContext(), SerieActivity.class));
                }
            }
        });
        radioButtonMovies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences preferences = getSharedPreferences("radio", MODE_PRIVATE);
                SharedPreferences.Editor editorRadio = preferences.edit();
                if(b){
                    editorRadio.putBoolean("radioMovie", true).apply();
                }
                else{
                    editorRadio.putBoolean("radioMovie", false).apply();
                }
            }
        });
        radioButtonSeries.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences preferences = getSharedPreferences("radio", MODE_PRIVATE);
                SharedPreferences.Editor editorRadio = preferences.edit();
                if(b){
                    editorRadio.putBoolean("radioSerie", true).apply();
                }
                else{
                    editorRadio.putBoolean("radioSerie", false).apply();
                }
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

//    public void checkSerieOrMovie(){
//
//
//
//    }

    public void getUsername(TextView username){
        movieViewModel.getAllUsers().observe(Profile.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users){
                    String name = user.getUser_name();
                    username.setText("Hi " + name);

                    String token = user.getToken();

                    Map<String, String> header = new HashMap<>();
                    header.put("Authorization", "Bearer " + token);
                    Log.d("checkHeader", header.toString());
                }
            }
        });



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
    @Override
    public void onPause(){
        super.onPause();
        save(genreAction.isChecked());
        save(genreAdventure.isChecked());
        save(genreComedy.isChecked());
        save(genreCrime.isChecked());
        save(genreFantasy.isChecked());
        save(genreThriller.isChecked());

        save(radioButtonSeries.isChecked());
        save(radioButtonMovies.isChecked());
    }
    @Override
    public void onResume(){
        super.onResume();
        genreAction.setChecked(loadAction());
        genreAdventure.setChecked(loadAdventure());
        genreComedy.setChecked(loadComedy());
        genreCrime.setChecked(loadCrime());
        genreFantasy.setChecked(loadFantasy());
        genreThriller.setChecked(loadThriller());

        radioButtonSeries.setChecked(loadSerie());
        radioButtonMovies.setChecked(loadMovie());
    }

    private void save(final boolean isChecked){
        SharedPreferences sharedPreferences = getSharedPreferences("check", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("checkAction", genreAction.isChecked()).apply();
        editor.putBoolean("checkAdventure", genreAdventure.isChecked()).apply();
        editor.putBoolean("checkComedy", genreComedy.isChecked()).apply();
        editor.putBoolean("checkCrime", genreCrime.isChecked()).apply();
        editor.putBoolean("checkFantasy", genreFantasy.isChecked()).apply();
        editor.putBoolean("checkThriller", genreThriller.isChecked()).apply();
        editor.commit();

    }
    private boolean loadAction(){
        SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
        return sharedPreferences.getBoolean("checkAction", false);
    }
    private boolean loadAdventure(){
        SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
        return sharedPreferences.getBoolean("checkAdventure", false);
    }
    private boolean loadComedy(){
        SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
        return sharedPreferences.getBoolean("checkComedy", false);
    }
    private boolean loadCrime(){
        SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
        return sharedPreferences.getBoolean("checkCrime", false);
    }
    private boolean loadFantasy(){
        SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
        return sharedPreferences.getBoolean("checkFantasy", false);
    }
    private boolean loadThriller(){
        SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
        return sharedPreferences.getBoolean("checkThriller", false);
    }
    private boolean loadSerie(){
        SharedPreferences sharedPreferences = getSharedPreferences("radio",MODE_PRIVATE);
        return sharedPreferences.getBoolean("radioSerie", false);
    }
    private boolean loadMovie(){
        SharedPreferences sharedPreferences = getSharedPreferences("radio",MODE_PRIVATE);
        return sharedPreferences.getBoolean("radioMovie", false);
    }
}