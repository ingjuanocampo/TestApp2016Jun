package com.juanocampo.test.androidtest.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by juanocampo on 6/19/16.
 */
public class ProxyApp {

    private final Context context;
    private Itunes itunes;

    public ProxyApp(Context context) {
        this.context = context;
    }

    public Itunes getItunesIntance() {
        if (isNetworkAvailable()) {
            itunes = new ItunesApiClientImp(context);
        } else {
            itunes = new ItunesCacheImp(context);
        }
     return itunes;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
