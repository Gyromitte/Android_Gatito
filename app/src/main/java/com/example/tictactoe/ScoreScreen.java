package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        Intent intent = getIntent();
        if (intent != null) {
            String winner = intent.getStringExtra("winner");
            int playerXScore = intent.getIntExtra("playerXScore", 0);
            int playerOScore = intent.getIntExtra("playerOScore", 0);

            TextView winnerTextView = findViewById(R.id.winnerTextView);
            winnerTextView.setText("Winner: " + winner);

            TextView playerScoresTextView = findViewById(R.id.playerScoresTextView);
            playerScoresTextView.setText("Player X: " + playerXScore + ", Player O: " + playerOScore);
        }
    }
}

