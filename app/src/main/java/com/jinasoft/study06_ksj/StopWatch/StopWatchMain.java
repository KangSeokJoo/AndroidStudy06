package com.jinasoft.study06_ksj.StopWatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.jinasoft.study06_ksj.R;

import java.util.Locale;

public class StopWatchMain extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch_main);

        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running); // 키값과 변수 저장장
        outState.putBoolean("wasRunning", wasRunning);
       super.onSaveInstanceState(outState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (wasRunning){
            running = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView textView = (TextView)findViewById(R.id.Stop_ViewTV);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int house = seconds/3600;
                int minute = (seconds%3600) / 60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", house, minute, secs);
                textView.setText(time);
                if (running){
                    seconds++;
                }handler.postDelayed(this, 1000);
            }
        });

    }
}
