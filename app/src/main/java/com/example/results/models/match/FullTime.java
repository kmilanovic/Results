package com.example.results.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullTime {

    @SerializedName("homeTeam")
    @Expose
    private Integer homeTeam;
    @SerializedName("awayTeam")
    @Expose
    private Integer awayTeam;

    public Integer getHomeTeam() {
        return homeTeam;
    }

    public Integer getAwayTeam() {
        return awayTeam;
    }

}
