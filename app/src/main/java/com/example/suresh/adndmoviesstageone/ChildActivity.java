package com.example.suresh.adndmoviesstageone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suresh.adndmoviesstageone.Utilities.MovieInfo;
import com.squareup.picasso.Picasso;

public class ChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        ImageView detailPoster = findViewById(R.id.details_poster);
        TextView detailOriginalTitle = findViewById(R.id.details_original_title);
        TextView detailPlotSynopsis = findViewById(R.id.details_plot_synopsis);
        TextView detailReleaseDate = findViewById(R.id.details_release_date);
        TextView detailUserRating = findViewById(R.id.details_user_rating);

        Intent intent = getIntent();
        if(intent.hasExtra("clicked_item")) {
            MovieInfo movieInfo = (MovieInfo) intent.getSerializableExtra("clicked_item");
            Picasso.with(this).load(movieInfo.getMoviePoster()).into(detailPoster);
            detailOriginalTitle.setText(getString(R.string.detail_movie_title) + movieInfo.getOriginalTitle());
            detailPlotSynopsis.setText(getString(R.string.detail_movie_plot) + movieInfo.getPlotSynopsis());
            detailUserRating.setText(getString(R.string.detail_movie_rating) + movieInfo.getUserRating());
            detailReleaseDate.setText(getString(R.string.detail_movie_release) + movieInfo.getReleaseDate());
        }
    }
}