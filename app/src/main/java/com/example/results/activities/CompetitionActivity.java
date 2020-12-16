package com.example.results.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.results.adapters.CompetitionAdapter;
import com.example.results.R;

public class CompetitionActivity extends AppCompatActivity {

    private static final String KEY_ID = "competition_id";
    private static final String KEY_NAME = "competition_name";
    private int competitionId;
    private String competitionName;

    public int getCompetitionId() {
        return competitionId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);

        Intent intent = getIntent();
        competitionId = intent.getIntExtra("competitionId", 0);
        competitionName = intent.getStringExtra("competitionName");

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_ID)) {
                competitionId = savedInstanceState.getInt(KEY_ID);
            }

            if (savedInstanceState.containsKey(KEY_NAME)) {
                competitionName = savedInstanceState.getString(KEY_NAME);
            }
        }

        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle(competitionName);

        CompetitionAdapter adapter = new CompetitionAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.view_pagerCompetition);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_ID, competitionId);
        bundle.putString(KEY_NAME, competitionName);
    }

}
