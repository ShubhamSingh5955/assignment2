package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ArrayList<user> userData = new ArrayList<>();
    private RecyclerView rvUser;
    private RequestQueue requestQueue;
    String url = "https://gorest.co.in/public-api/users";
    private userAdapter adapter;
    ProgressBar prLoadder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("User Details");

        rvUser = findViewById(R.id.rvUser);
        prLoadder = findViewById(R.id.prLoadder);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        prLoadder.setVisibility(View.VISIBLE);
        getData();

    }

    private void getData() {

        StringRequest s = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject j1 = new JSONObject(response);
              //      String  s1 = j1.getString("code");
                  //  if (s1=="200") {

                        JSONArray arr = j1.getJSONArray("data");

                        Log.e("TAG", "onResponse: "+arr );
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject j3 = arr.getJSONObject(i);
                            Log.e("TAG", "onResponse: "+j3 );

                            userData.add(new user(j3.getInt("id"),j3.getString("name"),j3.getString("email"),j3.getString("gender"),j3.getString("status"),j3.getString("created_at"),j3.getString("updated_at")));
                        }


                        adapter = new userAdapter(userData, getApplicationContext());
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        rvUser.setAdapter(adapter);
                        rvUser.setLayoutManager(layoutManager);

                           prLoadder.setVisibility(View.INVISIBLE);

                 //   } else {

                        JSONObject j2 = j1.getJSONObject("data");
                        Toast.makeText(getApplicationContext(), j2.getString("error"), Toast.LENGTH_SHORT).show();
                 //   }

                } catch (JSONException e) {
                    e.printStackTrace();
                    prLoadder.setVisibility(View.INVISIBLE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("SOME ERROR  ::: " + error);
            }
        });
        requestQueue.add(s);
    }
}


