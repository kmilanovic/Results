package com.example.results.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.results.R;
import com.example.results.models.player.Player;

import java.util.ArrayList;
import java.util.Objects;

public class PlayersAwayTeamAdapter extends RecyclerView.Adapter<PlayersAwayTeamAdapter.PlayersAwayTeamViewHolder>  {

    private ArrayList<Player> playerList;
    private final ListItemClickListener listener;

    public PlayersAwayTeamAdapter(ArrayList<Player> playerList, ListItemClickListener clickListener) {
        this.playerList = playerList;
        this.listener = clickListener;
    }


    @Override
    public PlayersAwayTeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new PlayersAwayTeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder( PlayersAwayTeamViewHolder holder, int position) {
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

    public class PlayersAwayTeamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerView recyclerView;
        TextView textPlayerNo;
        TextView textPlayerName;
        TextView textPlayerPosition;

        PlayersAwayTeamViewHolder(View itemView)
        {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerViewAwayTeamPlayers);
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
                    name = "/";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            listener.onListItemClick(position, id, name);
        }
    }
}
