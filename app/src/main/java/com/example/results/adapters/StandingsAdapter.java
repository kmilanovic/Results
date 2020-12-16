package com.example.results.adapters;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.results.R;
import com.example.results.models.standings.Table;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder> {

    private static final String LOG_TAG = StandingsAdapter.class.getSimpleName();
    private List<Table> list;
    private Activity act;

    public StandingsAdapter(Activity activity, List<Table> standingList) {
        list = standingList;
        act = activity;
    }
    @Override
    public StandingsAdapter.StandingsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new StandingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StandingsAdapter.StandingsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return R.layout.standings_custom_layout;
    }

    class StandingsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageStandingTeam;
        TextView textTeamPosition;
        TextView textTeamName;
        TextView textTeamPlayed;
        TextView textTeamDifference;
        TextView textTeamPoints;

        StandingsViewHolder(View itemView) {
            super(itemView);

            imageStandingTeam = itemView.findViewById(R.id.image_team_logo);
            textTeamPosition = itemView.findViewById(R.id.text_table_position);
            textTeamName = itemView.findViewById(R.id.text_team_name);
            textTeamPlayed = itemView.findViewById(R.id.text_team_played);
            textTeamDifference = itemView.findViewById(R.id.text_team_difference);
            textTeamPoints = itemView.findViewById(R.id.text_team_points);

            itemView.setTag(this);
        }

        void bind(int i) {
            Integer teamPosition = list.get(i).getTablePosition();
            String teamName = list.get(i).getTableTeam().getTeamName();
            final String teamLogo = list.get(i).getTableTeam().getTeamLogo();
            Integer teamPlayed = list.get(i).getTablePlayed();
            Integer teamDifference = list.get(i).getTableDifference();
            Integer teamPoints = list.get(i).getTablePoints();

            if(teamLogo == null || teamLogo.isEmpty())
            {
                Picasso.get()
                        .cancelRequest(imageStandingTeam);
            }
            else
            {
                Picasso.get()
                        .load(teamLogo)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(imageStandingTeam, new Callback() {
                            @Override
                            public void onSuccess() {
                            }
                            @Override
                            public void onError(Exception e) {
                                if (!act.isFinishing()) {
                                    GlideToVectorYou.init()
                                            .with(act)
                                            .load(Uri.parse(teamLogo), imageStandingTeam);
                                }
                            }
                        });
            }
            textTeamPosition.setText(String.format("%d", teamPosition)+".");
            textTeamName.setText(teamName);
            textTeamPlayed.setText(String.format("%d", teamPlayed));
            textTeamDifference.setText(String.format("%d", teamDifference));
            textTeamPoints.setText(String.format("%d", teamPoints));
        }
    }
}
