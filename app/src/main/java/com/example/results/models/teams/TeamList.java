package com.example.results.models.teams;

import com.example.results.models.competition.Competition;
import com.example.results.models.player.Player;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamList {

    @SerializedName("count")
    @Expose
    private Integer countTeams;

    @SerializedName("competition")
    @Expose
    private Competition teamCompetition;

    @SerializedName("teams")
    @Expose
    private List<Team> teamList;

    private List<Player> squad;

    public Integer getCountTeams() {
        return countTeams;
    }

    public Competition getTeamCompetition() {
        return teamCompetition;
    }

    public List<Team> getTeams() {
        return teamList;
    }

    public List<Player> getSquad()
    {
        return squad;
    }
}
