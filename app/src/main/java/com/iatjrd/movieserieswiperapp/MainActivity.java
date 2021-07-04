package com.iatjrd.movieserieswiperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileButton = findViewById(R.id.profileButton);

        profileButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String token = bundle.getString("token");
                Log.d("ProfileToken", token);
                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
    }
}