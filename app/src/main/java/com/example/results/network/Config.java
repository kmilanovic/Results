package com.example.results.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Config {

    public static final String BASE_KEY = "X-Auth-Token: 531ea605f73e4117b7cb10a8fc76b9aa";

    private static Config instance = new Config();
    private static Context context;

    public static Config getInstance(Context c) {
        context = c.getApplicationContext();
        return instance;
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
