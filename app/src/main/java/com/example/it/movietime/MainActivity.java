package com.example.it.movietime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.it.movietime.adapter.MovieAdapter;
import com.example.it.movietime.adapter.OnClickListener;
import com.example.it.movietime.constant.APIConfig;
import com.example.it.movietime.model.Movie;
import com.example.it.movietime.utilities.NetworkUtils;
import com.example.it.movietime.utilities.PreferencesUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    static String TAG = MainActivity.class.getSimpleName();
    RecyclerView rv_movie;
    List<Movie> list;
    MovieAdapter adapter;

    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_movie=(RecyclerView)findViewById(R.id.rv_movielist);
        list = new ArrayList<>();
        adapter=new MovieAdapter(list);
        gson=new Gson();
        populateData();
        setupRecycler();
    }

    private void setupRecycler() {

        rv_movie.setLayoutManager(new GridLayoutManager(getApplicationContext(),
                2));
        rv_movie.setAdapter(adapter);
    }

    private void populateData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("api_key",
                BuildConfig.API_KEY);
        int shortInt = new PreferencesUtils().getShort(getApplicationContext());
        NetworkUtils networkUtils = new NetworkUtils(this);
        networkUtils.doRequest(APIConfig.getPageMovie(shortInt),
                params,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: " + response);

                        JsonParser parser = new JsonParser();
                        JsonObject element = (JsonObject)parser.parse(response);

                        JsonElement responseWrapper = element.get("results");

                        list.addAll((ArrayList<Movie>) gson.fromJson(responseWrapper,
                                new TypeToken<ArrayList<Movie>>(){}.getType()));

                        Log.d(TAG, "onResponse: " + list.size());
                        adapter.notifyDataSetChanged();
                        adapter.setClickListener(MainActivity.this);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null)
                        {
                            Log.d(TAG, "onErrorResponse: " + error.getMessage());
                        }
                        else
                        {
                            Log.d(TAG, "onErrorResponse: Something wrong");
                        }
                    }
                }
        );
    }


    @Override
    public void onItemClick(Movie data, int position) {
        Toast.makeText(this, "aaa" + position, Toast.LENGTH_SHORT).show();
    }
}
