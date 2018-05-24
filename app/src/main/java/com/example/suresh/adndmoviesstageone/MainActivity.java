package com.example.suresh.adndmoviesstageone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.suresh.adndmoviesstageone.Utilities.DataFetch;
import com.example.suresh.adndmoviesstageone.Utilities.MovieAdaptor;
import com.example.suresh.adndmoviesstageone.Utilities.MovieInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdaptor.ItemOnClickListener {

    private final String apiKey = "cdebd4c255c625febb97e2f91c1ed9e5";
    private final String authority = "https://api.themoviedb.org";
    private MovieAdaptor movieAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FetchData fetchData = new FetchData();
        String userPreference = "PopularMovies";
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        movieAdaptor = new MovieAdaptor(new ArrayList<MovieInfo>(), this);

        fetchData.execute(userPreference, authority, apiKey);
        RecyclerView recyclerView = findViewById(R.id.main_frame);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(movieAdaptor);
    }

    @Override
    public void ItemClicked(int positionClicked) {
        Intent childActivityIntent = new Intent(MainActivity.this,
                ChildActivity.class);
        childActivityIntent.putExtra("clicked_item", movieAdaptor.getItem(positionClicked));
        startActivity(childActivityIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String userPreference = null;
        if(item.getItemId() == R.id.sort_popular) {
            userPreference = "PopularMovies";
        } else if (item.getItemId() == R.id.sort_rating) {
            userPreference = "PopularRating";
        }
        new FetchData().execute(userPreference, authority, apiKey);
        return true;
    }

    /*
     *Async Task to perform API data fetch on a background thread
     */
    @SuppressLint("StaticFieldLeak")
    class FetchData extends AsyncTask<String, String, ArrayList<MovieInfo>> {

        @Override
        protected ArrayList<MovieInfo> doInBackground(String... strings) {
            DataFetch dataFetch = new DataFetch();
            return dataFetch.makeAPICall(strings[0], strings[1], strings[2]);
        }

        @Override
        protected void onPostExecute(ArrayList<MovieInfo> movieCollections) {
            movieAdaptor.updateData(movieCollections);
        }
    }
}