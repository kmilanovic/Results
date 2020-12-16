package com.example.results.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.results.R;
import com.example.results.activities.PlayerInfoActivity;
import com.example.results.activities.PlayersActivity;
import com.example.results.adapters.ListItemClickListener;
import com.example.results.adapters.ListItemClickListener2;
import com.example.results.adapters.PlayersAwayTeamAdapter;
import com.example.results.adapters.PlayersHomeTeamAdapter;
import com.example.results.models.match.AwayTeam;
import com.example.results.models.match.Head2head;
import com.example.results.models.match.HomeTeam;
import com.example.results.models.match.Match;
import com.example.results.models.match.MatchGeneral;
import com.example.results.models.player.Player;
import com.example.results.models.teams.Team;
import com.example.results.network.RetrofitManager;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class SquadsFragment extends Fragment implements ListItemClickListener {

    private static final String LOG_TAG = SquadsFragment.class.getSimpleName();
    private ArrayList<Player> playerList;

    RecyclerView recyclerView, recyclerView2;
    LinearLayoutManager layoutManager;
    TextView tvHomeTeamName, tvAwayTeamName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_players_home_team2, container, false);


        PlayersActivity playersActivity = (PlayersActivity) getActivity();
        final int homeTeamId = playersActivity.getHomeTeamId();
        final int awayTeamId = playersActivity.getAwayTeamId();
        String homeTeamNameActivity = playersActivity.getHomeTeamName();
        String awayTeamNameActivity = playersActivity.getAwayTeamName();

        tvHomeTeamName = view.findViewById(R.id.text_homeTeamName);
        tvAwayTeamName = view.findViewById(R.id.text_awayTeamName);
        final String homeName = homeTeamNameActivity;
        final String awayName = awayTeamNameActivity;

        tvHomeTeamName.setText(homeName);
        tvAwayTeamName.setText(awayName);
        tvHomeTeamName.setTypeface(tvHomeTeamName.getTypeface(), Typeface.BOLD);
        tvAwayTeamName.setTypeface(tvAwayTeamName.getTypeface(), Typeface.BOLD);


        recyclerView = view.findViewById(R.id.recyclerViewHomeTeamPlayers);
        recyclerView2 = view.findViewById(R.id.recyclerViewAwayTeamPlayers);
        layoutManager = new LinearLayoutManager(this.getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());

        Call<Team> call = RetrofitManager.getInstance().getApi().getPlayers(homeTeamId);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                final Team res = response.body();

                if (res != null) {

                    playerList = new ArrayList<>(res.getSquadList());

                    PlayersHomeTeamAdapter adapter = new PlayersHomeTeamAdapter(playerList, SquadsFragment.this);
                    recyclerView.setAdapter(adapter);

                    DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
                    recyclerView.addItemDecoration(divider);
                } else {
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

        Call<Team> call2 = RetrofitManager.getInstance().getApi().getPlayers(awayTeamId);
        call2.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                final Team res = response.body();

                if (res != null) {

                    playerList = new ArrayList<>(res.getSquadList());

                    Log.d(LOG_TAG, "matchList:" + playerList.size());

                    PlayersAwayTeamAdapter adapter = new PlayersAwayTeamAdapter(playerList, SquadsFragment.this);
                    recyclerView2.setAdapter(adapter);

                    DividerItemDecoration divider = new DividerItemDecoration(recyclerView2.getContext(), layoutManager.getOrientation());
                    recyclerView2.addItemDecoration(divider);
                } else {
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        return view;
    }


    @Override
    public void onListItemClick(int clickedItemIndex, int clickedItemId, String clickedItemName) {
        Intent intent = new Intent(this.getActivity(), PlayerInfoActivity.class);
        intent.putExtra("playerId", clickedItemId);
        startActivity(intent);
    }
}
