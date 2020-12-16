package com.example.results.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.results.R;
import com.example.results.models.player.Player;
import com.example.results.network.RetrofitManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerInfoActivity extends AppCompatActivity {

    TextView textPlayerName;
    TextView textPlayerDOB;
    TextView textPlayerCOB;
    TextView textPlayerNationality;
    TextView textPlayerPosition;
    TextView textPlayerNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_info_custom);

        Intent intent = getIntent();
        final int playerId = intent.getIntExtra("playerId", 0);

        textPlayerName = findViewById(R.id.text_player_name);
        textPlayerDOB = findViewById(R.id.text_player_dob);
        textPlayerCOB = findViewById(R.id.text_player_cob);
        textPlayerNationality = findViewById(R.id.text_player_nationality);
        textPlayerPosition = findViewById(R.id.text_player_position);
        textPlayerNumber = findViewById(R.id.text_player_number);


        Call<Player> call = RetrofitManager.getInstance().getApi().getPlayerInfo(playerId);
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                Player res = response.body();

                if (res != null) {
                    String playerName = res.getName();
                    String playerDob = res.getDateOfBirth();
                    String playerCob = res.getCountryOfBirth();
                    String playerNationality = res.getNationality();
                    String playerPosition = res.getPosition();

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = formatter.parse(playerDob);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
                    dateFormatter.setTimeZone(TimeZone.getDefault());
                    String playerDateOfBirth = dateFormatter.format(date);

                    textPlayerName.setText(playerName);
                    textPlayerDOB.setText(playerDateOfBirth);
                    textPlayerCOB.setText(playerCob);
                    textPlayerNationality.setText(playerNationality);
                    textPlayerPosition.setText(playerPosition);
                    textPlayerNumber.setText("-");


                    if(res.getShirtNumber() == null)
                    {
                        textPlayerNumber.setText("-");
                    }
                    else
                    {
                        textPlayerNumber.setText(res.getShirtNumber().toString());
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}
