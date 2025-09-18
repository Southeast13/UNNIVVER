package com.example.mysimpleapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private TextView scoreText;
    private Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.gameView);
        scoreText = findViewById(R.id.scoreText);
        resetBtn = findViewById(R.id.resetBtn);

        gameView.setOnScoreChangeListener(new GameView.OnScoreChangeListener() {
            @Override
            public void onScoreChanged(int newScore) {
                scoreText.setText("Score: " + newScore);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.resetGame();
            }
        });
    }
}
