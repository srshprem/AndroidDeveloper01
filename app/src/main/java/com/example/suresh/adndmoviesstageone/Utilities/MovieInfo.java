package com.example.suresh.adndmoviesstageone.Utilities;

import java.io.Serializable;

public class MovieInfo implements Serializable{

    private final String originalTitle;
    private final String moviePoster;
    private final String plotSynopsis;
    private final String userRating;
    private final String releaseDate;

    public MovieInfo (String originalTitle, String moviePoster, String plotSynopsis, String userRating, String releaseDate) {
        this.originalTitle = originalTitle;
        this.moviePoster = moviePoster;
        this.plotSynopsis = plotSynopsis;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public String getUserRating() {
        return userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}

