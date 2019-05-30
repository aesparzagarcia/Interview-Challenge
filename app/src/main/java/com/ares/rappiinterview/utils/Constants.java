package com.ares.rappiinterview.utils;

import android.os.Build;

import com.ares.rappiinterview.BuildConfig;

/**
 * Created by ares on 2019-05-27
 */
public class Constants {

    // Environment
    public static final String API_KEY = BuildConfig.PUBLIC_KEY;
    public static final String API_URL = "https://firebasetestpeber.herokuapp.com/files/";

    public static final int HTTP_REQUEST_TIMEOUT = 60;

    // API headers
    public static final String API_HEADER_KEY = "API_KEY";
    public static final String API_HEADER_CONTENT_TYPE = "Content-Type";
    public static final String API_HEADER_CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
    public static final String API_HEADER_AUTH = "Authorization";

    // API body
    // API body
    public static final String API_BODY_KEY_GRANT_TYPE = "grant_type";
    public static final String API_BODY_KEY_REFRESH_TOKEN = "refresh_token";
    public static final int API_BODY_ERROR_CODE_INVALID_TOKEN = 10;

    //Preferences
    public static final String PREFERENCES_USER_TOKEN = "pref:user:token";
    public static final String PREFERENCES_USER_ACCESS_TOKEN = "pref:user:access:token";
    public static final String PREFERENCES_USER_REFRESH_TOKEN = "pref:user:refresh:token";
    public static final String PREFERENCES_POPULAR_MOVIE_LIST = "pref:movie:popular:list";
    public static final String PREFERENCES_TOP_RATED_MOVIE_LIST = "pref:movie:top:list";
    public static final String PREFERENCES_UP_COMING_MOVIE_LIST = "pref:movie:coming:list";
}
