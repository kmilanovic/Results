package com.example.results.models.player;

import com.example.results.models.competition.Competition;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Player {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private Object lastName;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("countryOfBirth")
    @Expose
    private String countryOfBirth;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("shirtNumber")
    @Expose
    private Integer shirtNumber;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;


    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }


    public String getCountryOfBirth() {
        return countryOfBirth;
    }


    public String getNationality() {
        return nationality;
    }


    public String getPosition() {
        return position;
    }


    public Integer getShirtNumber() {
        return shirtNumber;
    }

    public Player(String name, String dateOfBirth, String countryOfBirth, String nationality, String position, Integer shirtNumber)
    {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.countryOfBirth = countryOfBirth;
        this.nationality = nationality;
        this.position = position;
        this.shirtNumber = shirtNumber;
    }




}
