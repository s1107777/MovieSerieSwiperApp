package com.iatjrd.movieserieswiperapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.iatjrd.movieserieswiperapp.model.MovieViewModel;
import com.iatjrd.movieserieswiperapp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {

    EditText lEmail, lPassword;
    Button lLoginBtn;
    TextView lRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lEmail = findViewById(R.id.email);
        lPassword = findViewById(R.id.password);
        lLoginBtn = findViewById(R.id.loginBtn);
        lRegisterBtn = findViewById(R.id.createText);

        lLoginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                checkUsername();
            }

        });

        lRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }

    void checkUsername(){
        boolean isValid = true;
        String email = lEmail.getText().toString().trim();
        String password = lPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            lEmail.setError("Enter your email to log in!");
            isValid = false;
        }else {
            if(!isEmail(lEmail)){
                lEmail.setError("Use a vaild email!");
                isValid = false;
            }
        }

        if(TextUtils.isEmpty(password)){
            lPassword.setError("Enter your password!");
            isValid = false;
        }

        if(isValid){
            login(email, password);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }


    public void login(String email, String password) {
        String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("checkResponse", response);
                        String responseString = response;
                        JSONObject json = null;
                        JSONObject jsonUser;
                        try {
                            json = new JSONObject(responseString);
                            String users = json.getString("user");
                            jsonUser = new JSONObject(users);
                            String userId = jsonUser.getString("id");
                            Log.d("userId", userId);
                            String token = json.getString("access_token");
                            Log.d("userToken", token);
                            String name = jsonUser.getString("name");
                            Log.d("userName", name);
                            User user = new User(userId, name, token);
                            MovieViewModel.insertUser(user);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(Login.this, "Successvolly logged in!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("loginError", error.toString());
                        Toast.makeText(Login.this, "Login Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}