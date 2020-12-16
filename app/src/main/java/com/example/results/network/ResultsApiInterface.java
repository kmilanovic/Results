package com.example.results.network;

import com.example.results.models.competition.CompetitionList;
import com.example.results.models.match.Head2head;
import com.example.results.models.match.MatchGeneral;
import com.example.results.models.match.MatchList;
import com.example.results.models.player.Player;
import com.example.results.models.standings.Standings;
import com.example.results.models.teams.Squad;
import com.example.results.models.teams.Team;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ResultsApiInterface {

    @Headers(Config.BASE_KEY)
    @GET("/v2/competitions")
    Call<CompetitionList> getCompetitions();

    @Headers(Config.BASE_KEY)
    @GET("/v2/competitions/{id}/matches")
    Call<MatchList> getMatches(@Path("id") int id);

    @Headers(Config.BASE_KEY)
    @GET("/v2/teams/{id}")
    Call<Team> getPlayers(@Path("id") int id);

    @Headers(Config.BASE_KEY)
    @GET("/v2/players/{id}")
    Call<Player> getPlayerInfo(@Path("id") int id);

    @Headers(Config.BASE_KEY)
    @GET("/v2/matches/{id}")
    Call<MatchGeneral> getH2H(@Path("id") int id);

    @Headers(Config.BASE_KEY)
    @GET("/v2/competitions/{id}/standings")
    Call<Standings> getStandings(@Path("id") int id);



}
