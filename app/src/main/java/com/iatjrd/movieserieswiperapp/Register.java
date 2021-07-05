package com.iatjrd.movieserieswiperapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {

    private EditText rName, rEmail, rPassword;
    private Button rRegisterBtn;
    private TextView rLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rName = findViewById(R.id.fullName);
        rEmail = findViewById(R.id.email);
        rPassword = findViewById(R.id.password);
        rRegisterBtn = findViewById(R.id.loginBtn);
        rLoginBtn = findViewById(R.id.createText);

        rRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();

            }
        });

//        rLoginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }


    void checkData(){
        boolean isValid = true;
        final String name = this.rName.getText().toString().trim();
        final String email = this.rEmail.getText().toString().trim();
        final String password = this.rPassword.getText().toString().trim();


        if(TextUtils.isEmpty(name)){
            rName.setError(("Enter yur name"));
            isValid = false;
            return;
        }

        if (TextUtils.isEmpty(email)){
            rEmail.setError("Enter your email");
            isValid = false;
            return;
        }
        if (TextUtils.isEmpty(password)){
            rPassword.setError("Enter a password");
            isValid = false;
            return;
        }
        if(password.length() < 6){
            rPassword.setError("Password must be more than 6 characters.");
            isValid = false;
        }

        if(isValid){
            register(name, email, password);
            startActivity(new Intent(getApplicationContext(), Login.class));

        }
    }

    public void text_click (View view){
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    public void register(String name, String email, String password) {
        String url = "https://movieserieswiperdb-qioab.ondigitalocean.app/api/auth/register";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("checkResponse", response);
                        Toast.makeText(Register.this, "Successfully registered" + response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("registerError", error.toString());
                        Toast.makeText(Register.this, "Registered Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}