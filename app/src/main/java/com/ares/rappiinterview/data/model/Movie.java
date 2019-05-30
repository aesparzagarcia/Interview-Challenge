package com.ares.rappiinterview.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

/**
 * Created by ares on 2019-05-25
 */

public class Movie {

    int page;
    int total_results;
    int total_pages;
    public List<results> results;

    public Movie(int page, int total_results, int total_pages, List<Movie.results> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    @NonNull
    @Override
    public String toString() {
        String value = "";

        if(results == null){
            for(Movie.results result : results){
                value += "\n name: " + result.title;
            }
        }
        return value;
    }

    @Entity(tableName = "movie_table")
    public static class results {

        public String poster_path;
        @PrimaryKey(autoGenerate = true)
        public int id;
        public String title;
        public boolean adult;
        public String overview;
        public String release_date;
        public String original_title;
        public String original_lenguage;
        public String backdrop_path;
        public Double popularity;
        public int vote_count;
        public boolean video;
        public Double vote_average;

    }
}
