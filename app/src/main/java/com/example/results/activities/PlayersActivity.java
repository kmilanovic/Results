package com.example.results.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.results.R;
import com.example.results.adapters.PlayersAdapter;



public class PlayersActivity extends AppCompatActivity {

    private static final String KEY_ID = "homeTeamId";
    private static final String KEY_NAME = "homeTeamName";
    private int homeTeamId;
    private String homeTeamName;

    private static final String KEY_ID2 = "awayTeamId";
    private static final String KEY_NAME2 = "awayTeamName";
    private int awayTeamId;
    private String awayTeamName;

    private static final String KEY_ID3 = "matchId";
    private int matchId;

    public int getMatchId()
    {
        return matchId;
    }


    public int getHomeTeamId()
    {
        return homeTeamId;
    }

    public String getHomeTeamName()
    {
        return homeTeamName;
    }

    public int getAwayTeamId()
    {
        return awayTeamId;
    }

    public String getAwayTeamName()
    {
        return awayTeamName;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        Intent intent = getIntent();
        homeTeamId = intent.getIntExtra("homeTeamId", 0);
        homeTeamName = intent.getStringExtra("homeTeamName");
        awayTeamId = intent.getIntExtra("awayTeamId", 0);
        awayTeamName = intent.getStringExtra("awayTeamName");
        matchId = intent.getIntExtra("matchId", 0);


        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_ID)) {
                homeTeamId = savedInstanceState.getInt(KEY_ID);
            }
            if (savedInstanceState.containsKey(KEY_ID2)) {
                awayTeamId = savedInstanceState.getInt(KEY_ID2);
            }
            if (savedInstanceState.containsKey(KEY_NAME)) {
                homeTeamName = savedInstanceState.getString(KEY_NAME);
            }
            if (savedInstanceState.containsKey(KEY_NAME2)) {
                awayTeamName = savedInstanceState.getString(KEY_NAME2);
            }
            if (savedInstanceState.containsKey(KEY_ID3)) {
                matchId = savedInstanceState.getInt(KEY_ID3);
            }
        }

        PlayersAdapter adapter = new PlayersAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pagerPlayers);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_ID, homeTeamId);
        bundle.putString(KEY_NAME, homeTeamName);
        bundle.putInt(KEY_ID2, awayTeamId);
        bundle.putString(KEY_NAME2, awayTeamName);
        bundle.putInt(KEY_ID3, matchId);
    }

}
