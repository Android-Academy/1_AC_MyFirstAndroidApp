package com.vullnetlimani.myapplication.activites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vullnetlimani.myapplication.R;

import java.util.ArrayList;
import java.util.Random;

public class TicTacToeGame extends AppCompatActivity {

    int ActivePlayer = 1;
    ArrayList<Integer> Player1 = new ArrayList();
    ArrayList<Integer> Player2 = new ArrayList();
    private LinearLayout winner_layout;
    private TextView messageTextView;
    private FloatingActionButton mFabButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tic_tac_toe_layout);

        winner_layout = findViewById(R.id.winner_layout);
        messageTextView = findViewById(R.id.messageTextView);
        mFabButton = findViewById(R.id.mFabButton);


        mFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }

    public void ButtonClick(View view) {
        Button buttonSelected = (Button) view;
        int CellId = 0;
        switch ((buttonSelected.getId())) {

            case R.id.bt_1:
                CellId = 1;
                break;
            case R.id.bt_2:
                CellId = 2;
                break;

            case R.id.bt_3:
                CellId = 3;
                break;

            case R.id.bt_4:
                CellId = 4;
                break;

            case R.id.bt_5:
                CellId = 5;
                break;

            case R.id.bt_6:
                CellId = 6;
                break;

            case R.id.bt_7:
                CellId = 7;
                break;
            case R.id.bt_8:
                CellId = 8;
                break;
            case R.id.bt_9:
                CellId = 9;
                break;
        }


        PlayGame(CellId, buttonSelected);

    }

    private void PlayGame(int CellId, Button buttonSelected) {

        if (ActivePlayer == 1) {
            buttonSelected.setText("X");
            buttonSelected.setBackgroundColor(getResources().getColor(R.color.player_one_color));
            Player1.add(CellId);
            ActivePlayer = 2;


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    autoPlay();
                }
            },200);


        } else if (ActivePlayer == 2) {
            buttonSelected.setText("O");
            buttonSelected.setBackgroundColor(getResources().getColor(R.color.player_two_color));
            Player2.add(CellId);
            ActivePlayer = 1;
        }

        buttonSelected.setEnabled(false);

        CheckWinner();
    }

    private void CheckWinner() {

        int Winner = -1;

        //row 1
        if (Player1.contains(1) && Player1.contains(2) && Player1.contains(3)) {
            Winner = 1;
        }

        if (Player2.contains(1) && Player2.contains(2) && Player2.contains(3)) {
            Winner = 2;
        }

        //row 2
        if (Player1.contains(4) && Player1.contains(5) && Player1.contains(6)) {
            Winner = 1;
        }

        if (Player2.contains(4) && Player2.contains(5) && Player2.contains(6)) {
            Winner = 2;
        }

        //row 3
        if (Player1.contains(7) && Player1.contains(8) && Player1.contains(9)) {
            Winner = 1;
        }

        if (Player2.contains(7) && Player2.contains(8) && Player2.contains(9)) {
            Winner = 2;
        }

        //col 1
        if (Player1.contains(1) && Player1.contains(4) && Player1.contains(7)) {
            Winner = 1;
        }

        if (Player2.contains(1) && Player2.contains(4) && Player2.contains(7)) {
            Winner = 2;
        }

        //col 2
        if (Player1.contains(2) && Player1.contains(5) && Player1.contains(8)) {
            Winner = 1;
        }

        if (Player2.contains(2) && Player2.contains(5) && Player2.contains(8)) {
            Winner = 2;
        }

        //col 3
        if (Player1.contains(3) && Player1.contains(6) && Player1.contains(9)) {
            Winner = 1;
        }

        if (Player2.contains(3) && Player2.contains(6) && Player2.contains(9)) {
            Winner = 2;
        }

        //Diagonale 1
        if (Player1.contains(1) && Player1.contains(5) && Player1.contains(9)) {
            Winner = 1;
        }

        if (Player2.contains(1) && Player2.contains(5) && Player2.contains(9)) {
            Winner = 2;
        }

        //Diagonale 2
        if (Player1.contains(3) && Player1.contains(5) && Player1.contains(7)) {
            Winner = 1;
        }

        if (Player2.contains(3) && Player2.contains(5) && Player2.contains(7)) {
            Winner = 2;
        }


        if (Winner != -1) {

            if (Winner == 1) {
                messageTextView.setText(R.string.player_1_win);
            } else {
                messageTextView.setText(R.string.player_2_win);
            }

            disableAllButtons();
            winner_layout.setVisibility(View.VISIBLE);

        } else {
            messageTextView.setText(R.string.player_tie);

            if (Player1.size() >= 5 && Player2.size() >= 4) {
                winner_layout.setVisibility(View.VISIBLE);
            }

        }

    }

    private void autoPlay() {

        ArrayList<Integer> EmptyCells = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            if (!(Player1.contains(i) || Player2.contains(i))) {
                EmptyCells.add(i);
            }
        }

        Random myRandom = new Random();

        int ranIndex = myRandom.nextInt(EmptyCells.size()-0)+0; // if size = 3, select 0,1,2
        int CellID = EmptyCells.get(ranIndex);

        Button btnSelected;
        switch (CellID) {
            case 1:
                btnSelected = findViewById(R.id.bt_1);
                break;
            case 2:
                btnSelected = findViewById(R.id.bt_2);
                break;
            case 3:
                btnSelected = findViewById(R.id.bt_3);
                break;
            case 4:
                btnSelected = findViewById(R.id.bt_4);
                break;
            case 5:
                btnSelected = findViewById(R.id.bt_5);
                break;
            case 6:
                btnSelected = findViewById(R.id.bt_6);
                break;
            case 7:
                btnSelected = findViewById(R.id.bt_7);
                break;
            case 8:
                btnSelected = findViewById(R.id.bt_8);
                break;
            case 9:
                btnSelected = findViewById(R.id.bt_9);
                break;
            default:
                btnSelected = findViewById(R.id.bt_1);
                break;
        }

        PlayGame(CellID, btnSelected);

    }

    private void disableAllButtons() {

        findViewById(R.id.bt_1).setEnabled(false);
        findViewById(R.id.bt_2).setEnabled(false);
        findViewById(R.id.bt_3).setEnabled(false);
        findViewById(R.id.bt_4).setEnabled(false);
        findViewById(R.id.bt_5).setEnabled(false);
        findViewById(R.id.bt_6).setEnabled(false);
        findViewById(R.id.bt_7).setEnabled(false);
        findViewById(R.id.bt_8).setEnabled(false);
        findViewById(R.id.bt_9).setEnabled(false);

    }

}
