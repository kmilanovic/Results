package com.example.results.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Score {
    private String duration;
    @SerializedName("fullTime")
    @Expose
    private FullTime fullTime;

    public FullTime getFullTime() {
        return fullTime;
    }

}
