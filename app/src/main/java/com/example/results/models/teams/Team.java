package com.example.results.models.teams;

import com.example.results.models.player.Player;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {

    @SerializedName("id")
    @Expose
    private Integer teamId;

    @SerializedName("name")
    @Expose
    private String teamName;

    @SerializedName("crestUrl")
    @Expose
    private String teamLogo;

    @SerializedName("squad")
    @Expose
    private List<Player> playerList;

    public Integer getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public List<Player> getSquadList() {
        return playerList;
    }


}
