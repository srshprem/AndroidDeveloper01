package com.example.suresh.adndmoviesstageone.Utilities;

import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class DataFetch {

    /*Empty Constructor*/
    public DataFetch () {

    }

    public ArrayList<MovieInfo> makeAPICall(String userPreference, String authority, String apiKey) {
        String url = this.buildURL(userPreference, authority, apiKey);
        String response = this.getResponse(url);
        return this.parseResponse(response);
    }

    /*Build URL*/
    private String buildURL(String userPreference, String authority, String apiKey) {
        Uri uri = null;
        if(userPreference.equalsIgnoreCase("PopularMovies")) {
            uri = Uri.parse(authority).buildUpon().appendPath("3").appendPath("movie").appendPath("popular").appendQueryParameter("api_key",apiKey).build();
        } else if (userPreference.equalsIgnoreCase("PopularRating")) {
            uri = Uri.parse(authority).buildUpon().appendPath("3").appendPath("movie").appendPath("top_rated").appendQueryParameter("api_key",apiKey).build();
        }
        return uri.toString();
    }

    /*Get Response*/
    private String getResponse(String url) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL urlWeb = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) urlWeb.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();

            while(line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /*Parse Response*/
    private ArrayList parseResponse (String response) {
        ArrayList movieCollection = new ArrayList <MovieInfo>();

        try {
            JSONObject root = new JSONObject(response);
            JSONArray results = root.getJSONArray("results");
            int counter = 0;

            while (counter < results.length()) {
                JSONObject jsonObject = results.getJSONObject(counter);
                movieCollection.add(new MovieInfo(jsonObject.getString("original_title"),
                                                    "http://image.tmdb.org/t/p/w185/" + jsonObject.getString("poster_path"),
                                                    jsonObject.getString("overview"),
                                                    jsonObject.getString("vote_average"),
                                                    jsonObject.getString("release_date")));
                counter++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movieCollection;
    }
}
