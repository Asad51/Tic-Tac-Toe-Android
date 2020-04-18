package com.github.asad51.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout winnerLayout, playLayout;
    private int winningPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4,
            8}, {2, 4, 6}};
    private int gameStatus[], player, winner, hitCount;
    private boolean isGameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playLayout = findViewById(R.id.playLayout);
        winnerLayout = findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);
        playLayout.setVisibility(View.VISIBLE);

        player = Constant.Player.O;
        gameStatus = new int[9];
        for (int i = 0; i < 9; i++)
            gameStatus[i] = -1;
        isGameOver = false;
    }

    public void onClickGrid(View view) {
        DrawableView v = (DrawableView) view;
        int tag = Integer.parseInt(v.getTag().toString());

        if (gameStatus[tag] != -1 || isGameOver)
            return;

        if (player == Constant.Player.O) {
            v.drawShape(Constant.Shape.O, "#F2EBD3");
        } else {
            v.drawShape(Constant.Shape.X, "#545454");
        }
        v.invalidate();

        gameStatus[tag] = player;
        hitCount++;

        if (checkWinner()) {
            isGameOver = true;
            showWinner();
        } else if (hitCount == 9) {
            isGameOver = true;
            showWinner();
        } else
            player = (player == Constant.Player.O) ? Constant.Player.X : Constant.Player.O;
    }

    private boolean checkWinner() {
        for (int i = 0; i < winningPositions.length; i++) {
            boolean isWinner = true;
            for (int j = 0; j < winningPositions[i].length; j++) {
                if (gameStatus[winningPositions[i][j]] != player) {
                    isWinner = false;
                    break;
                }
            }
            if (isWinner)
                return true;
        }
        return false;
    }

    private void showWinner() {

    }
}
