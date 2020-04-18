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
    //DrawableView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playLayout = findViewById(R.id.playLayout);
        winnerLayout = findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);
        playLayout.setVisibility(View.VISIBLE);
    }

    public void onClickGrid(View view){

    }
}
