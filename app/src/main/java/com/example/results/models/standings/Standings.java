package com.example.results.models.standings;

import com.example.results.models.competition.Competition;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Standings {

    @SerializedName("competition")
    @Expose
    private Competition standingCompetition;

    @SerializedName("standings")
    @Expose
    private List<Standing> standingList;

    public List<Standing> getStandingList() {
        return standingList;
    }
}
