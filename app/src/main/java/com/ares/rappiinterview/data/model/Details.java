package com.ares.rappiinterview.data.model;

import androidx.annotation.NonNull;

/**
 * Created by ares on 2019-05-27
 */
public class Details {

    public String title;
    public String overview;
    public Double vote_average;
    public String release_date;
    public int runtime;
    public String backdrop_path;

    @NonNull
    @Override
    public String toString() {
        return "Title = " + title;
    }

    public Details(String title, String overview, Double vote_average, String release_date, int runtime, String backdrop_path) {
        this.title = title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.runtime = runtime;
        this.backdrop_path = backdrop_path;
    }
}
