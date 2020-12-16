package com.example.results.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Head2head {

    @SerializedName("head2head")
    @Expose
    private Head2head head2head;

    @SerializedName("numberOfMatches")
    @Expose
    private Integer numberOfMatches;
    @SerializedName("totalGoals")
    @Expose
    private Integer totalGoals;
    @SerializedName("homeTeam")
    @Expose
    private HomeTeam homeTeam;
    @SerializedName("awayTeam")
    @Expose
    private AwayTeam awayTeam;

    private Match match;

    public Integer getNumberOfMatches() {
        return numberOfMatches;
    }

    public Integer getTotalGoals() {
        return totalGoals;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }
    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public Match getMatch()
    {
        return match;
    }
}
