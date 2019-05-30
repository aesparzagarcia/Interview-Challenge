package com.ares.rappiinterview.data.model;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ares on 2019-05-27
 */
@StringDef({GrantType.API_KEY, GrantType.REQUEST_TOKEN})
@Retention(RetentionPolicy.SOURCE)
public @interface GrantType {
    String API_KEY = "api_key";
    String REQUEST_TOKEN = "request_token";
}
