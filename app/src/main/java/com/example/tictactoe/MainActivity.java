package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[] buttons = new Button[9]; // Array para los botones
    private boolean isPlayerXTurn = true; // Variable para alternar entre los jugadores
    private int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asignar los botones por su ID
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);

        // Agregar OnClickListener a los botones
        for (Button button : buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;

        // Verificar si la celda está vacía y si el juego no ha terminado
        if (button.getText().toString().equals("") && !checkForWinner()) {
            if (isPlayerXTurn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            isPlayerXTurn = !isPlayerXTurn; // Alternar el turno de los jugadores

            // Actualizar el texto en el encabezado para mostrar de quién es el turno
            TextView playerInfoTextView = findViewById(R.id.playerInfoTextView);
            playerInfoTextView.setText(isPlayerXTurn ? "X Turn" : "O Turn");
        }

        // Verificar si hay un ganador o un empate
        if (checkForWinner()) {
            // Mostrar un mensaje según el resultado
            String winner = isPlayerXTurn ? "O" : "X";
            Toast.makeText(this, "Player " + winner + " wins!", Toast.LENGTH_SHORT).show();

            // Obtén las puntuaciones actuales de los jugadores
            int playerXScore = 0; // Reemplaza con la puntuación real del jugador X
            int playerOScore = 0; // Reemplaza con la puntuación real del jugador O

            // Envia las puntuaciones y el ganador a la pantalla de puntuación
            Intent intent = new Intent(MainActivity.this, ScoreScreen.class);
            intent.putExtra("playerXScore", playerXScore);
            intent.putExtra("playerOScore", playerOScore);
            intent.putExtra("winner", winner);
            startActivity(intent);

        } else if (isBoardFull()) {
            Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show();
            // Envia que es un empate a la pantalla de puntuación
            Intent intent = new Intent(MainActivity.this, ScoreScreen.class);
            intent.putExtra("isDraw", true);
            startActivity(intent);
        }
    }

    private boolean checkForWinner() {
        String[] cellTexts = new String[9];

        for (int i = 0; i < 9; i++) {
            cellTexts[i] = buttons[i].getText().toString();
        }

        for (int[] pattern : winPatterns) {
            if (!cellTexts[pattern[0]].isEmpty() &&
                    cellTexts[pattern[0]].equals(cellTexts[pattern[1]]) &&
                    cellTexts[pattern[1]].equals(cellTexts[pattern[2]])) {
                return true;
            }
        }

        return false;
    }

    private boolean isBoardFull() {
        for (Button button : buttons) {
            if (button.getText().toString().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}