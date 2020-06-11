package com.star.amlakonline;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.star.amlakonline.ApiConnection.LoginConnection;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    public Timer timer;
    public static StartActivity startActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startActivity = this;
        timer = new Timer("Timer");
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(isNetworkConnected()){
                    Intent intent = new Intent(StartActivity.this,MainActivity.class);
                    intent.putExtra("fromStart",true);
                    startActivity(intent);
                    timer.cancel();
                }
            }
        };
        long delay = 3000L;
        timer.scheduleAtFixedRate(timerTask, delay,delay);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
