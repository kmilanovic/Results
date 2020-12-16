package com.example.results.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.results.R;
import com.example.results.fragments.H2HFragment;
import com.example.results.fragments.SquadsFragment;

public class PlayersAdapter extends FragmentPagerAdapter {

    private static final int TABS = 2;
    private Context context;

    public PlayersAdapter(Context con, FragmentManager fm) {
        super(fm);
        context = con;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SquadsFragment();
        } else if(position == 1) {
            return new H2HFragment();
        }
        else
        {
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.squads);
        } else if (position == 1){
            return context.getString(R.string.h2h);
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
