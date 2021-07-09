package com.iatjrd.movieserieswiperapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iatjrd.movieserieswiperapp.adapter.SavedItemStackAdapter;
import com.iatjrd.movieserieswiperapp.model.MovieViewModel;
import com.iatjrd.movieserieswiperapp.model.SavedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SavedItemActivity extends AppCompatActivity {
    public RecyclerView sList;
    public LinearLayoutManager linearLayoutManager;
    public DividerItemDecoration dividerItemDecoration;
    public List<SavedItem> savedItem;
    public SavedItemStackAdapter adapter;
    ImageButton profileButton, homeButton;

    public MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveditem);

        Log.d("enteredSaved", "entered save activity");

        sList = findViewById(R.id.savedmovie_list);

        savedItem = new ArrayList<>();
        adapter = new SavedItemStackAdapter(savedItem);


        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        dividerItemDecoration = new DividerItemDecoration(sList.getContext(), linearLayoutManager.getOrientation());

        sList.setHasFixedSize(true);
        sList.setLayoutManager(linearLayoutManager);
        sList.addItemDecoration(dividerItemDecoration);
        sList.setAdapter(adapter);

        profileButton = findViewById(R.id.profileButton);
        homeButton = findViewById(R.id.homeButton);

        profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        movieViewModel = new ViewModelProvider.AndroidViewModelFactory(SavedItemActivity.this.getApplication())
                .create(MovieViewModel.class);

        movieViewModel.getAllSavedItems().observe(this, new Observer<List<SavedItem>>() {
            @Override
            public void onChanged(List<SavedItem> savedItems) {
                adapter.setSavedItemsList(savedItems);
                adapter.notifyDataSetChanged();
                //textView.setText(builder.toString());

            }
        });


       /*String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/saved";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        SavedItem savedItem = new SavedItem();
                        movieViewModel.insertSaveItem(savedItem);
                        adapter.notifyDataSetChanged();
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e("Volley", error.toString());
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }*/

    }
}

