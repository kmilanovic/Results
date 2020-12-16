package com.example.results.models.match;

import com.example.results.models.competition.Competition;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Match {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("competition")
    @Expose
    private Competition matchCompetition;

    @SerializedName("season")
    @Expose
    private Season season;

    @SerializedName("utcDate")
    @Expose
    private String utcDate;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("matchday")
    @Expose
    private Object matchday;

    @SerializedName("stage")
    @Expose
    private String stage;

    @SerializedName("score")
    @Expose
    private Score score;

    @SerializedName("homeTeam")
    @Expose
    private HomeTeam homeTeam;

    @SerializedName("awayTeam")
    @Expose
    private AwayTeam awayTeam;

    private Head2head head2head;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Competition getMatchCompetition() {
        return matchCompetition;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMatchday() {
        return matchday;
    }

    public void setMatchday(Object matchday) {
        this.matchday = matchday;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeId(){
        return homeTeam.getId();
    }

    public int getAwayId(){
        return awayTeam.getId();
    }

    public Head2head getHead2head()
    {
        return head2head;
    }
}
