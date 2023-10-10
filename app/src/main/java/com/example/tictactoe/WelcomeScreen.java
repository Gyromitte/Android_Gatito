package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000;  // Duración en milisegundos (5 segundos)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        // Handler para esperar 5 segundos y luego pasar a otra actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Luego de 5 segundos, inicia la otra actividad
                Intent intent = new Intent(WelcomeScreen.this, MainActivity.class);
                startActivity(intent);
                finish();  // Cierra esta actividad para evitar volver a ella con el botón "atrás"
            }
        }, SPLASH_DURATION);
    }
}