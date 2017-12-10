package com.example.it.movietime.constant;

/**
 * Created by Mangpali on 12/9/2017.
 */

public class APIConfig {
    public static final String HOST_URL="https://api.themoviedb.org/3/";
    public static final String PAGE_MOVIE="movie";
    public static final String PAGE_MOVIE_POPULAR="popular";

    public static String getMoviePosterURL(String dim, String file)
    {
        return  "http://image.tmdb.org/t/p/" + dim + file;
    }

    public static String getPageMovie(int shortInt) {
        return getMoviePopular();

    }

    private static String getMoviePopular() {
        return HOST_URL + PAGE_MOVIE + "/" + PAGE_MOVIE_POPULAR;
    }
}
