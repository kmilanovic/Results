package com.example.results.models.competition;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CompetitionList {

    @SerializedName("competitions")
    @Expose
    private ArrayList<Competition> competitions = null;


    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }

}
