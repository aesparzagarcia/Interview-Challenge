package com.ares.rappiinterview.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.ares.rappiinterview.RappiApp;
import com.ares.rappiinterview.data.model.Movie;
import com.ares.rappiinterview.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.grandcentrix.tray.AppPreferences;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ares on 2019-05-25
 */
public class SessionManager {

    private AppPreferences appPreferences;
    /**
     * Used to Registration and verification process only
     */
    private SharedPreferences registrationPrefs;

    public SessionManager(AppPreferences appPreferences, RappiApp context) {
        this.appPreferences = appPreferences;
        registrationPrefs = context.getSharedPreferences("regvalPrefs", Context.MODE_PRIVATE);
    }

    public void clearData() {
        appPreferences.clear();
    }

    public String getUserToken() {
        return appPreferences.getString(Constants.PREFERENCES_USER_TOKEN, null);
    }

    public void setUserToken(String userToken) {
        appPreferences.put(Constants.PREFERENCES_USER_TOKEN, userToken);
    }

    public String getUserAccessToken() {
        return appPreferences.getString(Constants.PREFERENCES_USER_ACCESS_TOKEN, null);
    }

    public void setUserAccessToken(String accessToken) {
        appPreferences.put(Constants.PREFERENCES_USER_ACCESS_TOKEN, accessToken);
    }

    public void setPopularMoviesInfo(List<Movie.results> movies){
        Gson gson = new Gson();
        String movieList = gson.toJson(movies);
        appPreferences.put(Constants.PREFERENCES_POPULAR_MOVIE_LIST, movieList);
    }

    public List<Movie.results> getPopularMovieList(){
        List<Movie.results> movieList;
        String jsonList = appPreferences.getString(Constants.PREFERENCES_POPULAR_MOVIE_LIST, null);
        if(jsonList != null && !jsonList.isEmpty()){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Movie.results>>() {}.getType();
            movieList = gson.fromJson(jsonList, type);
        } else {
            movieList = new ArrayList<>();
        }

        return movieList;
    }

    public void setTopRatedMoviesInfo(List<Movie.results> movies){
        Gson gson = new Gson();
        String movieList = gson.toJson(movies);
        appPreferences.put(Constants.PREFERENCES_TOP_RATED_MOVIE_LIST, movieList);
    }

    public List<Movie.results> getTopRatedMovieList(){
        List<Movie.results> movieList;
        String jsonList = appPreferences.getString(Constants.PREFERENCES_TOP_RATED_MOVIE_LIST, null);
        if(jsonList != null && !jsonList.isEmpty()){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Movie.results>>() {}.getType();
            movieList = gson.fromJson(jsonList, type);
        } else {
            movieList = new ArrayList<>();
        }

        return movieList;
    }

    public void setUpComingMoviesInfo(List<Movie.results> movies){
        Gson gson = new Gson();
        String movieList = gson.toJson(movies);
        appPreferences.put(Constants.PREFERENCES_UP_COMING_MOVIE_LIST, movieList);
    }

    public List<Movie.results> getUpComingMovieList(){
        List<Movie.results> movieList;
        String jsonList = appPreferences.getString(Constants.PREFERENCES_UP_COMING_MOVIE_LIST, null);
        if(jsonList != null && !jsonList.isEmpty()){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Movie.results>>() {}.getType();
            movieList = gson.fromJson(jsonList, type);
        } else {
            movieList = new ArrayList<>();
        }

        return movieList;
    }
}
