package com.example.results.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MatchList {

    @SerializedName("matches")
    @Expose
    private ArrayList<Match> matches = null;

    public ArrayList<Match> getMatches() {
        return matches;
    }
}
