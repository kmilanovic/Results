package com.example.results.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.results.R;
import com.example.results.activities.PlayersActivity;
import com.example.results.models.match.MatchGeneral;
import com.example.results.network.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class H2HFragment extends Fragment {

    private static final String LOG_TAG = H2HFragment.class.getSimpleName();

    TextView tvHomeTeamName, tvAwayTeamName, tvWinsLabel, tvNumberOfHomeTeamWins, tvNumberOfAwayTeamWins, tvDrawsLabel, tvNumberOfHomeTeamDraws, tvNumberOfAwayTeamDraws, tvLosesLabel,tvNumberOfHomeTeamLosses, tvNumberOfAwayTeamLosses, tvNumberOfGoals;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.h2h_custom_layout, container, false);

        PlayersActivity playersActivity = (PlayersActivity) getActivity();
        final int matchId = playersActivity.getMatchId();
        String homeTeamNameActivity = playersActivity.getHomeTeamName();
        String awayTeamNameActivity = playersActivity.getAwayTeamName();

        tvHomeTeamName = view.findViewById(R.id.text_home_team);
        tvAwayTeamName = view.findViewById(R.id.text_away_team);
        tvWinsLabel = view.findViewById(R.id.text_wins);
        tvNumberOfHomeTeamWins = view.findViewById(R.id.text_numberOfHomeTeamWins);
        tvNumberOfAwayTeamWins = view.findViewById(R.id.text_numberOfAwayTeamWins);
        tvDrawsLabel = view.findViewById(R.id.text_draws);
        tvNumberOfHomeTeamDraws = view.findViewById(R.id.text_numberOfHomeDraws);
        tvNumberOfAwayTeamDraws = view.findViewById(R.id.text_numberOfAwayDraws);
        tvLosesLabel = view.findViewById(R.id.text_loses);
        tvNumberOfHomeTeamLosses = view.findViewById(R.id.text_numberOfHomeLoses);
        tvNumberOfAwayTeamLosses = view.findViewById(R.id.text_numberOfAwayLoses);
        tvNumberOfGoals = view.findViewById(R.id.text_numberOfGoalsScored);

        final String homeName = homeTeamNameActivity;
        final String awayName = awayTeamNameActivity;

        tvHomeTeamName.setText(homeName);
        tvAwayTeamName.setText(awayName);
        tvHomeTeamName.setTypeface(tvHomeTeamName.getTypeface(), Typeface.BOLD);
        tvAwayTeamName.setTypeface(tvAwayTeamName.getTypeface(), Typeface.BOLD);

        Call<MatchGeneral> call3 = RetrofitManager.getInstance().getApi().getH2H(matchId);
        call3.enqueue(new Callback<MatchGeneral>() {
            @Override
            public void onResponse(Call<MatchGeneral> call, Response<MatchGeneral> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                final MatchGeneral res = response.body();

                if (res != null) {
                    tvNumberOfGoals.setText(String.valueOf(res.getHead2head().getTotalGoals()));
                    tvNumberOfHomeTeamWins.setText(String.valueOf(res.getHead2head().getHomeTeam().getWins()));
                    tvNumberOfAwayTeamWins.setText(String.valueOf(res.getHead2head().getAwayTeam().getWins()));
                    tvNumberOfHomeTeamDraws.setText(String.valueOf(res.getHead2head().getHomeTeam().getDraws()));
                    tvNumberOfAwayTeamDraws.setText(String.valueOf(res.getHead2head().getAwayTeam().getDraws()));
                    tvNumberOfHomeTeamLosses.setText(String.valueOf(res.getHead2head().getHomeTeam().getLosses()));
                    tvNumberOfAwayTeamLosses.setText(String.valueOf(res.getHead2head().getAwayTeam().getLosses()));

                    if(Integer.valueOf(res.getHead2head().getHomeTeam().getWins()) > res.getHead2head().getAwayTeam().getWins())
                    {
                        tvNumberOfHomeTeamWins.setTextColor(Color.parseColor("#008000"));
                        tvNumberOfAwayTeamWins.setTextColor(Color.parseColor("#FE0000"));
                    }
                    else
                    {
                        tvNumberOfHomeTeamWins.setTextColor(Color.parseColor("#FE0000"));
                        tvNumberOfAwayTeamWins.setTextColor(Color.parseColor("#008000"));
                    }

                    if(Integer.valueOf(res.getHead2head().getHomeTeam().getWins()) == res.getHead2head().getAwayTeam().getWins())
                    {
                        tvNumberOfHomeTeamWins.setTextColor(Color.parseColor("#9E770B"));
                        tvNumberOfAwayTeamWins.setTextColor(Color.parseColor("#9E770B"));
                    }

                    if(Integer.valueOf(res.getHead2head().getHomeTeam().getLosses()) > res.getHead2head().getAwayTeam().getLosses())
                    {
                        tvNumberOfHomeTeamLosses.setTextColor(Color.parseColor("#FE0000"));
                        tvNumberOfAwayTeamLosses.setTextColor(Color.parseColor("#008000"));
                    }
                    else
                    {
                        tvNumberOfHomeTeamLosses.setTextColor(Color.parseColor("#008000"));
                        tvNumberOfAwayTeamLosses.setTextColor(Color.parseColor("#FE0000"));
                    }

                    if(Integer.valueOf(res.getHead2head().getHomeTeam().getLosses()) == res.getHead2head().getAwayTeam().getLosses())
                    {
                        tvNumberOfHomeTeamLosses.setTextColor(Color.parseColor("#9E770B"));
                        tvNumberOfAwayTeamLosses.setTextColor(Color.parseColor("#9E770B"));
                    }

                    tvNumberOfHomeTeamDraws.setTextColor(Color.parseColor("#9E770B"));
                    tvNumberOfAwayTeamDraws.setTextColor(Color.parseColor("#9E770B"));

                } else {
                }
            }

            @Override
            public void onFailure(Call<MatchGeneral> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        return view;
    }
}
