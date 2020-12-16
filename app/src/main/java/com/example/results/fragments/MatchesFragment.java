package com.example.results.fragments;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.results.activities.CompetitionActivity;
import com.example.results.R;
import com.example.results.activities.PlayersActivity;
import com.example.results.adapters.ListItemClickListener2;
import com.example.results.adapters.MatchesAdapter;
import com.example.results.models.match.AwayTeam;
import com.example.results.models.match.HomeTeam;
import com.example.results.models.match.Match;
import com.example.results.models.match.MatchList;
import com.example.results.network.RetrofitManager;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Context;

import static java.lang.String.valueOf;


public class MatchesFragment extends Fragment implements ListItemClickListener2 {

    //private static final String LOG_TAG = MatchesFragment.class.getSimpleName();
    private ArrayList<Match> matchList;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Integer mYear;
    Integer mMonth;
    Integer mDay;
    private Button bDisplayDate;

    public MatchesFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_matches, container, false);
        //Log.d(LOG_TAG, LOG_TAG + " ACTIVE");

        CompetitionActivity competitionActivity = (CompetitionActivity) getActivity();
        int competitionId = competitionActivity.getCompetitionId();

        recyclerView = view.findViewById(R.id.recyclerViewMatches);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        bDisplayDate = view.findViewById(R.id.btnCalendar);

        //Kalendar za date picker
        Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        String dateString = String.format("%02d.%02d.%d", mDay, mMonth+1, mYear);

        bDisplayDate.setText(dateString);

        Call<MatchList> call = RetrofitManager.getInstance().getApi().getMatches(competitionId);
        call.enqueue(new Callback<MatchList>() {
            @Override
            public void onResponse(@NonNull Call<MatchList> call, @NonNull final Response<MatchList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                final MatchList res = response.body();
                if (res != null) {

                    matchList = new ArrayList<>(res.getMatches());
                    final ArrayList<Match> matchListByDate = new ArrayList<>();
                    for (Match match : matchList) {
                        if (match.getUtcDate().contains(bDisplayDate.getText().toString().format("%d-%02d-%02d", mYear, mMonth+1, mDay)))
                        {
                            matchListByDate.add(match);
                        }
                    }

                    final MatchesAdapter adapter = new MatchesAdapter(matchListByDate,MatchesFragment.this, getContext());
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(new MatchesAdapter.OnItemClickListener() {
                        @Override
                        public void onNotificationClick(int position) {

                            String homeTeam = matchListByDate.get(position).getHomeTeam().getName();
                            String awayTeam = matchListByDate.get(position).getAwayTeam().getName();
                            String time = matchListByDate.get(position).getUtcDate();

                            //Pretvorba sati u GMT i prikaz u notifikaciji
                            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                            formatter2.setTimeZone(TimeZone.getTimeZone("UTC"));
                            Date value = null;
                            try {
                                value = formatter2.parse(time);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            SimpleDateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy HH:mmaa");
                            dateFormatter2.setTimeZone(TimeZone.getDefault());
                            String dt2 = dateFormatter2.format(value).substring(11, 16);

                            //Prikazivanje alarm managera(notifikacije) 5 min prije utakmice
                            Intent intent = new Intent(getContext(), ReminderBroadcast.class);
                            intent.putExtra("homeTeam", homeTeam);
                            intent.putExtra("awayTeam", awayTeam);
                            intent.putExtra("time", dt2);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
                            AlarmManager alarmManager = (AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);


                            //Pretvorba datuma tipa string u tip date za dobivanje milisekunda
                            String matchTime = matchListByDate.get(position).getUtcDate();
                            //String matchTime = "2020-06-29T10:45:00Z";
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            format.setTimeZone(TimeZone.getTimeZone("UTC"));
                            Date date = null;
                            try {
                                date = format.parse(matchTime);
                                System.out.println(date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            //Log.d(LOG_TAG, "date:" + date);
                            SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                            dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                            String dt = dateFormatter.format(date);
                            //Log.d(LOG_TAG, "dt:" + dt);

                            //Pretvorba datuma u mili sekunde
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.getDefault());
                            //Log.d(LOG_TAG, "formatter:" + formatter);
                            LocalDateTime localDate = LocalDateTime.parse(dt, formatter);
                            //Log.d(LOG_TAG, "localDate:" + localDate);
                            long timeInMilliseconds = localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
                            //Log.d(LOG_TAG, "timeinmillis:" + timeInMilliseconds);
                            long currentTimeMilliseconds = System.currentTimeMillis();
                            long notificationShows = 300000;

                            if(timeInMilliseconds > currentTimeMilliseconds)
                            {
                                Toast.makeText(getContext(), "Match reminder between " + homeTeam + " - " + awayTeam + " has been set!",
                                        Toast.LENGTH_LONG).show();
                                alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMilliseconds - notificationShows, pendingIntent);
                                //Log.d(LOG_TAG, "matchList:" + (timeInMilliseconds - notificationShows));
                            }
                            else
                            {
                                Toast.makeText(getContext(), "Match is over!",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    bDisplayDate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    String dateString = String.format("%02d.%02d.%d", dayOfMonth, month+1, year);
                                    bDisplayDate.setText(dateString);
                                    matchListByDate.clear();

                                    ArrayList<Match> newList = new ArrayList<>();
                                    for (Match match : matchList) {

                                        if (match.getUtcDate().contains(bDisplayDate.getText().toString().format("%d-%02d-%02d", year, month+1, dayOfMonth)))
                                        {
                                            newList.add(match);
                                            adapter.notifyDataSetChanged();
                                        }
                                        else
                                        {
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                    matchListByDate.addAll(newList);

                                }
                            },mYear, mMonth, mDay);
                            datePickerDialog.show();
                        }
                    });

                } else {
                }
            }

            @Override
            public void onFailure(@NonNull Call<MatchList> call, @NonNull Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
        return view;
    }

    @Override
    public void onListItemClick2(int clickedItemIndex, int clickedItemId, String clickedItemCompetition, int homeTeamId, int awayTeamId, String homeTeamName, String awayTeamName) {
        Intent intent = new Intent(this.getActivity(), PlayersActivity.class);
        intent.putExtra("matchId", clickedItemId);
        intent.putExtra("homeTeamId", homeTeamId);
        intent.putExtra("awayTeamId", awayTeamId);
        intent.putExtra("homeTeamName", homeTeamName);
        intent.putExtra("awayTeamName", awayTeamName);
        startActivity(intent);
    }
}
