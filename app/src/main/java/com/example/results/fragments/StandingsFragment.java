package com.example.results.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.results.R;
import com.example.results.activities.CompetitionActivity;
import com.example.results.adapters.ListItemClickListener;
import com.example.results.adapters.StandingsAdapter;
import com.example.results.models.match.MatchList;
import com.example.results.models.standings.Standing;
import com.example.results.models.standings.Standings;
import com.example.results.models.standings.Table;
import com.example.results.network.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StandingsFragment extends Fragment {

    private static final String LOG_TAG = StandingsFragment.class.getSimpleName();
    private List<Standing> standingList;
    private List<Table> tableList;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_standings, container, false);

        Log.d(LOG_TAG, LOG_TAG + " ACTIVE");

        CompetitionActivity competitionActivity = (CompetitionActivity) getActivity();
        int competitionId = competitionActivity.getCompetitionId();

        recyclerView = view.findViewById(R.id.recylcerviewStandings);
        layoutManager = new LinearLayoutManager(this.getActivity());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        Call<Standings> call = RetrofitManager.getInstance().getApi().getStandings(competitionId);
        call.enqueue(new Callback<Standings>() {
            @Override
            public void onResponse(@NonNull Call<Standings> call, @NonNull Response<Standings> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                final Standings res = response.body();

                if (res != null) {

                    standingList = new ArrayList<>(res.getStandingList());

                    tableList = new ArrayList<>(standingList.get(0).getTableList());

                    StandingsAdapter adapter = new StandingsAdapter(StandingsFragment.this.getActivity(), tableList);
                    recyclerView.setAdapter(adapter);

                    DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
                    recyclerView.addItemDecoration(divider);
                } else {
                }
            }

            @Override
            public void onFailure(@NonNull Call<Standings> call, @NonNull Throwable t) {
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
