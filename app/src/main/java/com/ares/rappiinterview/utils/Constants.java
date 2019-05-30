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
}
