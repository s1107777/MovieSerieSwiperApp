package com.iatjrd.movieserieswiperapp;

import android.os.Bundle;
import android.util.Log;

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
import com.iatjrd.movieserieswiperapp.model.SavedItem;
import com.iatjrd.movieserieswiperapp.model.SavedItemViewModel;
import com.iatjrd.movieserieswiperapp.model.Serie;
import com.iatjrd.movieserieswiperapp.model.SerieViewModel;

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

    public SavedItemViewModel savedItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveditem);

        sList = findViewById(R.id.savedmovie_list);

        savedItem = new ArrayList<>();
        adapter = new SavedItemStackAdapter(savedItem);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(sList.getContext(), linearLayoutManager.getOrientation());

        sList.setHasFixedSize(true);
        sList.setLayoutManager(linearLayoutManager);
        sList.addItemDecoration(dividerItemDecoration);
        sList.setAdapter(adapter);

        savedItemViewModel = new ViewModelProvider.AndroidViewModelFactory(SavedItemActivity.this.getApplication())
                .create(SavedItemViewModel.class);

        savedItemViewModel.getAllSavedItems().observe(this, new Observer<List<SavedItem>>() {
            @Override
            public void onChanged(List<SavedItem> savedItems) {
                adapter.setSavedItemsList(savedItems);
                //textView.setText(builder.toString());

            }
        });

       /* String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/saved";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        SavedItem savedItem = new SavedItem(jsonObject.getString("name"), jsonObject.getString("genre"), "test", "test");
                        SavedItemViewModel.insert(savedItem);
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
        requestQueue.add(stringRequest);*/

    }

    }
