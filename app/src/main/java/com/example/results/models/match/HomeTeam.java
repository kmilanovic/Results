package com.example.results.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeTeam {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("wins")
    @Expose
    private Integer wins;
    @SerializedName("draws")
    @Expose
    private Integer draws;
    @SerializedName("losses")
    @Expose
    private Integer losses;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

}
