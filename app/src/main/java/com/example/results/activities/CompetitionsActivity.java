package com.example.results.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.results.R;
import com.example.results.adapters.CompetitionsAdapter;
import com.example.results.adapters.ListItemClickListener;
import com.example.results.models.competition.Competition;
import com.example.results.models.competition.CompetitionList;
import com.example.results.network.RetrofitManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompetitionsActivity extends AppCompatActivity implements ListItemClickListener {

    //private static final String LOG_TAG = CompetitionsActivity.class.getSimpleName();

    private ArrayList<Competition> competitionList;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    private static int[] freeCompetitionsArray = new int[]{2001,2017,2021,2003,2002,2015,2019,2014,2016,2013,2000,2018};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competitions);

        recyclerView = findViewById(R.id.recyclerViewCompetitions);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Call<CompetitionList> call = RetrofitManager.getInstance().getApi().getCompetitions();

        call.enqueue(new Callback<CompetitionList>() {
            @Override
            public void onResponse(Call<CompetitionList> call, Response<CompetitionList> response) {
                if (!response.isSuccessful()) {
                    //Log.e(LOG_TAG, "Neuspjesna konekcija " + response.code() + ": " + response.errorBody());
                    return;
                }
                //Log.d(LOG_TAG, "Uspjesna konekcija  " + response.code());
                final CompetitionList res = response.body();
                if (res != null) {
                    //Log.d(LOG_TAG, "Podaci iz " + response.body().toString());

                    competitionList = new ArrayList<>(res.getCompetitions());

                    ArrayList<Competition> freeCompetitions = new ArrayList<>();
                    for(Competition competition : competitionList)
                    {
                        if(arrayContainsValue(freeCompetitionsArray,competition.getId()))
                        {
                            freeCompetitions.add(competition);
                        }
                    }
                    //Log.d(LOG_TAG, "competitionList:" + freeCompetitions.size());
                    CompetitionsAdapter adapter = new CompetitionsAdapter(freeCompetitions, CompetitionsActivity.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    //Log.w(LOG_TAG, "Response je null!");
                }
            }

            @Override
            public void onFailure(Call<CompetitionList> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex, int clickedItemId, String clickedItemCompetition) {
        //String msg = "Item #" + clickedItemIndex + " [" + clickedItemCompetition + "] with id of " + clickedItemId + " clicked.";
        //Log.d(LOG_TAG, msg);
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, CompetitionActivity.class);
        intent.putExtra("competitionId", clickedItemId);
        intent.putExtra("competitionName", clickedItemCompetition);
        startActivity(intent);
    }

    private static boolean arrayContainsValue(int[] arr, Integer targetValue) {
        for(Integer s: arr){
            if(s.equals(targetValue))
                return true;
        }
        return false;
    }
}
