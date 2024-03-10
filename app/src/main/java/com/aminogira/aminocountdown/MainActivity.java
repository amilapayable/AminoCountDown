package com.aminogira.aminocountdown;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button incrementButton;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long durationInSeconds = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incrementButton = findViewById(R.id.incrementButton);
        timerTextView = findViewById(R.id.timerTextView);

        // Set initial text for timer
        updateTimerText();

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment duration by 1 minute
                durationInSeconds += 60;
                updateTimerText();
            }
        });
    }

    private void updateTimerText() {
        timerTextView.setText(formatTime(durationInSeconds));

        // Cancel the previous timer if running
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // Start new count down timer
        countDownTimer = new CountDownTimer(durationInSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                durationInSeconds = millisUntilFinished / 1000;
                timerTextView.setText(formatTime(durationInSeconds));
            }

            @Override
            public void onFinish() {
                timerTextView.setText("00:00");
            }
        }.start();
    }

    private String formatTime(long seconds) {
        long minutes = seconds / 60;
        long remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}