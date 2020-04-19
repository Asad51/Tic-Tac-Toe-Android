package com.github.asad51.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    TextView winnerTextView, statusTextView;
    Button startButton, restartButton, resetButton;

    private ConstraintLayout winnerLayout, playLayout;
    private DrawableView winnerView, drawViewO, drawViewX;
    private int winningPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4,
            8}, {2, 4, 6}};
    private int gameStatus[], player, hitCount;
    private boolean isGameOver;
    private int gamePlayed;
    private int[] winCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playLayout = findViewById(R.id.playLayout);
        winnerLayout = findViewById(R.id.winnerLayout);

        winnerView = findViewById(R.id.winnerView);
        drawViewO = findViewById(R.id.drawViewO);
        drawViewX = findViewById(R.id.drawViewX);

        winnerTextView = findViewById(R.id.winnerTextView);
        statusTextView = findViewById(R.id.statusTextView);
        startButton = findViewById(R.id.startButton);
        restartButton = findViewById(R.id.restartButton);
        resetButton = findViewById(R.id.resetButton);

        winCount = new int[3];

        initGame();
        resetGame();
    }

    public void onClickRestart(View view) {
        initGame();
    }

    public void onClickReset(View view) {
        resetGame();
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

        winnerLayout.setVisibility(View.INVISIBLE);
        playLayout.setVisibility(View.VISIBLE);
        winnerView.setVisibility(View.INVISIBLE);
        drawViewO.setVisibility(View.INVISIBLE);
        drawViewX.setVisibility(View.INVISIBLE);

        restartButton.setEnabled(false);
    }

    private void resetGame() {
        gamePlayed = winCount[0] = winCount[1] = winCount[2] = 0;
        resetButton.setEnabled(false);
        statusTextView.setText(R.string.status);
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
        restartButton.setEnabled(true);
        resetButton.setEnabled(true);
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
            winCount[player]++;
        } else {
            showWinnerView(drawViewO, Constant.Shape.O);
            showWinnerView(drawViewX, Constant.Shape.X);
            winnerTextView.setText(R.string.draw);
            winCount[2]++;
        }

        startButton.setVisibility(View.VISIBLE);
        statusTextView.setText("O: " + winCount[0] + ", X: " + winCount[1] + ", Draw: " + winCount[2]);
    }

    private void showWinnerView(DrawableView view, int shape) {
        view.setVisibility(View.VISIBLE);
        view.drawShape(shape, 6, 30);
        view.invalidate();
    }

}
