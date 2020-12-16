package com.example.results.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    static RetrofitManager instance;
    private ResultsApiInterface service;
    private RetrofitManager(){
        Retrofit.Builder builder = new Retrofit.Builder();
        //postavljanje retrofit-a
        Retrofit retrofit = builder.baseUrl("https://api.football-data.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ResultsApiInterface.class);
    }
    public static RetrofitManager getInstance(){
        if (instance == null){
            instance = new RetrofitManager();
        }
        return instance;
    }
    public ResultsApiInterface getApi(){
        return service;
    }

}
