package com.github.asad51.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    TextView winnerTextView, statusTextView;
    Button startButton;
    private ConstraintLayout winnerLayout, playLayout;
    private DrawableView winnerView, drawViewO, drawViewX;
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

        winnerView = findViewById(R.id.winnerView);
        drawViewO = findViewById(R.id.drawViewO);
        drawViewX = findViewById(R.id.drawViewX);

        winnerTextView = findViewById(R.id.winnerTextView);
        statusTextView = findViewById(R.id.statusTextView);
        startButton = findViewById(R.id.startButton);

        initGame();
    }

    public void onClickStartButton(View view) {
        initGame();
    }

    public void initGame() {
        player = Constant.Player.O;
        gameStatus = new int[9];
        for (int i = 0; i < 9; i++)
            gameStatus[i] = -1;
        isGameOver = false;
        hitCount = 0;

        int childCount = playLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            DrawableView child = (DrawableView) playLayout.getChildAt(i);
            child.drawShape(Constant.Shape.DEFAULT);
            child.invalidate();
        }
        statusTextView.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        winnerLayout.setVisibility(View.INVISIBLE);
        playLayout.setVisibility(View.VISIBLE);
        winnerView.setVisibility(View.INVISIBLE);
        drawViewO.setVisibility(View.INVISIBLE);
        drawViewX.setVisibility(View.INVISIBLE);
    }

    public void onClickGrid(View view) {
        DrawableView v = (DrawableView) view;
        int tag = Integer.parseInt(v.getTag().toString());

        if (gameStatus[tag] != -1 || isGameOver)
            return;

        if (player == Constant.Player.O) {
            v.drawShape(Constant.Shape.O);
        } else {
            v.drawShape(Constant.Shape.X);
        }
        v.invalidate();

        gameStatus[tag] = player;
        hitCount++;

        if (checkWinner()) {
            isGameOver = true;
            showWinner();
        } else if (hitCount == 9) {
            player = Constant.Winner.DRAW;
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
        playLayout.setVisibility(View.INVISIBLE);
        winnerLayout.setVisibility(View.VISIBLE);

        if (player == Constant.Winner.O || player == Constant.Winner.X) {
            showWinnerView(winnerView, player);
            winnerTextView.setText(R.string.winner);
        } else {
            showWinnerView(drawViewO, Constant.Shape.O);
            showWinnerView(drawViewX, Constant.Shape.X);
            winnerTextView.setText(R.string.draw);
        }

        startButton.setVisibility(View.VISIBLE);
    }

    private void showWinnerView(DrawableView view, int shape) {
        view.setVisibility(View.VISIBLE);
        view.drawShape(shape, 6, 30);
        view.invalidate();
    }

}
