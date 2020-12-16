package com.example.results.adapters;

import com.example.results.models.match.AwayTeam;
import com.example.results.models.match.HomeTeam;
import com.example.results.models.match.Match;

public interface ListItemClickListener2 {
    void onListItemClick2(int clickedItemIndex, int clickedItemId, String clickedItemCompetition, int homeTeamId, int awayTeamId, String homeTeamName, String awayTeamName);
}
