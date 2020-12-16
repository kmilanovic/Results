package com.example.results.models.match;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchGeneral {
    @SerializedName("head2head")
    @Expose
    private Head2head head2head;

    public Head2head getHead2head() {
        return head2head;
    }

}
