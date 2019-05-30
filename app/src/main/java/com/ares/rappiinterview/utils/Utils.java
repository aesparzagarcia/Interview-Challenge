package com.ares.rappiinterview.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ares on 2019-05-29
 */
public class Utils {

    private Context appContext;

    public Utils(Context context) {
        this.appContext = context;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) this.appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
