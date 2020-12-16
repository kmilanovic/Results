package com.example.results.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.results.R;
import com.example.results.fragments.MatchesFragment;
import com.example.results.fragments.StandingsFragment;

public class CompetitionAdapter extends FragmentPagerAdapter {

    private static final int TABS = 2;
    private Context context;

    public CompetitionAdapter(Context con, FragmentManager fm) {
        super(fm);
        context = con;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new MatchesFragment();
        }
        else if(position == 1){
            return new StandingsFragment();
        }
        else
        {
            return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return context.getString(R.string.category_matches);
        }
        else if(position == 1){
            return context.getString(R.string.category_standings);
        }
        else
        {
            return null;
        }
    }

    @Override
    public int getCount() {
        return TABS;
    }
}
