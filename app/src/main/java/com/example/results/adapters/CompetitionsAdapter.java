package com.example.results.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.results.R;
import com.example.results.models.competition.Competition;

import java.util.ArrayList;

public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.CompetitionsViewHolder> {

    private ListItemClickListener listener;
    private ArrayList<Competition> competitionList;

    public CompetitionsAdapter(ArrayList<Competition> competitionList, ListItemClickListener clickListener) {
        this.listener = clickListener;
        this.competitionList = competitionList;
    }

    @Override
    public CompetitionsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new CompetitionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompetitionsViewHolder holder, final int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return competitionList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.competitions_custom_layout;
    }

    public class CompetitionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvCompetitionName;
        CardView cardView;

        public CompetitionsViewHolder(View itemView)
        {
            super(itemView);
            tvCompetitionName = itemView.findViewById(R.id.competition_name);
            cardView =  itemView.findViewById(R.id.cardView);

            itemView.setOnClickListener(this);
        }

        void bind(int i)
        {
            String competitionName = competitionList.get(i).getName();
            tvCompetitionName.setText(competitionName);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            int id = competitionList.get(position).getId();
            String competition = competitionList.get(position).getName();
            listener.onListItemClick(position, id, competition);
        }
    }
}
