package com.example.results.adapters;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.results.R;
import com.example.results.models.competition.Competition;
import com.example.results.models.match.AwayTeam;
import com.example.results.models.match.HomeTeam;
import com.example.results.models.match.Match;

import java.nio.channels.Channel;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> {

    private static final String TAG = "MatchesAdapter";

    private ArrayList<Match> matchesList;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onNotificationClick(int position);
    }

    private final ListItemClickListener2 listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MatchesAdapter(ArrayList<Match> matchList, ListItemClickListener2 clickListener, Context context) {
        this.matchesList = matchList;
        this.listener = clickListener;
        this.context = context;
    }
    @Override
    public MatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new MatchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchesViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.matches_custom_layout;
    }


    public class MatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvHomeTeam;
        TextView tvHomeTeamResult;
        TextView tvAwayTeam;
        TextView tvAwayTeamResult;
        TextView tvMatchTime;
        CardView cardView;
        ImageButton notificationButton;

        MatchesViewHolder(View itemView)
        {
            super(itemView);
            tvHomeTeam = itemView.findViewById(R.id.text_home_team);
            tvHomeTeamResult = itemView.findViewById(R.id.text_home_wins);
            tvAwayTeam = itemView.findViewById(R.id.text_away_team);
            tvAwayTeamResult = itemView.findViewById(R.id.text_away_wins);
            tvMatchTime = itemView.findViewById(R.id.text_match_time);
            cardView = itemView.findViewById(R.id.cardView);
            notificationButton = itemView.findViewById(R.id.notification_button);

            itemView.setTag(this);
            itemView.setOnClickListener(this);


            notificationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            mListener.onNotificationClick(position);
                        }
                    }
                }
            });
        }

        void bind(int i) {
            String matchHomeTeam = matchesList.get(i).getHomeTeam().getName();
            String matchAwayTeam = matchesList.get(i).getAwayTeam().getName();
            String matchDateTime = matchesList.get(i).getUtcDate();

            //Pretvorba UTC zone u GMT
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = null;
            try {
                value = formatter.parse(matchDateTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mmaa");
            dateFormatter.setTimeZone(TimeZone.getDefault());
            String dt = dateFormatter.format(value);


            try {
                String matchHomeWins = String.valueOf(matchesList.get(i).getScore().getFullTime().getHomeTeam());
                String matchAwayWins = String.valueOf(matchesList.get(i).getScore().getFullTime().getAwayTeam());

                if (matchHomeWins == null || Objects.equals(matchHomeWins, "null")) {
                    matchHomeWins = "-";
                }
                tvHomeTeamResult.setText(matchHomeWins);

                if (matchAwayWins == null || Objects.equals(matchAwayWins, "null")) {
                    matchAwayWins = "-";
                }
                tvAwayTeamResult.setText(matchAwayWins);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            tvMatchTime.setText(dt.substring(11, 16));
            tvHomeTeam.setText(matchHomeTeam);
            tvAwayTeam.setText(matchAwayTeam);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            int homeId = matchesList.get(position).getHomeId();
            int awayId = matchesList.get(position).getAwayId();
            String homeTeamName = matchesList.get(position).getHomeTeam().getName();
            String awayTeamName = matchesList.get(position).getAwayTeam().getName();
            int id = matchesList.get(position).getId();
            String name = "";

            try {
                name = matchesList.get(position).getMatchCompetition().getName();
                id = matchesList.get(position).getMatchCompetition().getId();
                if (name == null || name.equals("null")) {
                    name = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            listener.onListItemClick2(position, id, name, homeId, awayId, homeTeamName, awayTeamName);
        }
    }
}
