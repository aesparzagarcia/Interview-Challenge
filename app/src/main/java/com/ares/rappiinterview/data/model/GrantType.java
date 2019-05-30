package com.ares.rappiinterview.data.model;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ares on 2019-05-27
 */
@StringDef({GrantType.PASSWORD, GrantType.REFRESH_TOKEN})
@Retention(RetentionPolicy.SOURCE)
public @interface GrantType {
    String PASSWORD = "password";
    String REFRESH_TOKEN = "refresh_token";
}
