package com.example.results.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.results.R;
import com.example.results.models.match.Match;
import com.example.results.models.player.Player;
import com.example.results.models.teams.Team;

import java.util.ArrayList;
import java.util.Objects;

public class PlayersHomeTeamAdapter extends RecyclerView.Adapter<PlayersHomeTeamAdapter.PlayersHomeTeamViewHolder>  {

    private ArrayList<Player> playerList;

    private static final String LOG_TAG = PlayersHomeTeamAdapter.class.getSimpleName();
    private final ListItemClickListener listener;

    public PlayersHomeTeamAdapter(ArrayList<Player> playerList, ListItemClickListener clickListener) {
        this.playerList = playerList;
        this.listener = clickListener;

        if (playerList == null) {
            Log.d(LOG_TAG, "Where art thy data?");
        }
    }

    @Override
    public PlayersHomeTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new PlayersHomeTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayersHomeTeamViewHolder holder, int position) {
        holder.bind(position);
    }


    @Override
    public int getItemCount() {
        return playerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.players_custom_layout;
    }

    public class PlayersHomeTeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textHomeTeamName;
        TextView textPlayerNo;
        TextView textPlayerName;
        TextView textPlayerPosition;

        PlayersHomeTeamViewHolder(View itemView)
        {
            super(itemView);

            textHomeTeamName = itemView.findViewById(R.id.text_homeTeamName);
            textPlayerNo = itemView.findViewById(R.id.text_player_no);
            textPlayerName = itemView.findViewById(R.id.text_player_name);
            textPlayerPosition = itemView.findViewById(R.id.text_player_position);

            itemView.setTag(this);
            itemView.setOnClickListener(this);
        }

        void bind(int i) {
            String playerName = playerList.get(i).getName();
            String playerPosition = playerList.get(i).getPosition();

            try {
                String playerNumber = String.valueOf(playerList.get(i).getShirtNumber());

                if (playerNumber == null || Objects.equals(playerNumber, "null")) {
                    playerNumber = "-";
                }
                textPlayerNo.setText(playerNumber);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            textPlayerName.setText(playerName);
            textPlayerPosition.setText(playerPosition);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            int id = playerList.get(position).getId();
            String name = "";

            try {
                name = playerList.get(position).getName();
                id = playerList.get(position).getId();
                if (name == null || name.equals("null")) {
                    name = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            listener.onListItemClick(position, id, name);
        }
    }
}
