package com.star.amlakonline;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.star.amlakonline.Model.File;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class IndexActivity extends AppCompatActivity {
    File f;
    public static IndexActivity indexActivity;
    public ProgressBar progressBar;
    public TextView fileTitle;
    public ImageButton back;
    public int per = 255;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        progressBar = findViewById(R.id.progressBar);
        back = findViewById(R.id.backBtn);
        fileTitle = findViewById(R.id.fileTitle);
        Gson g = new Gson();
        f =  g.fromJson(getIntent().getStringExtra("file"),File.class);
        fileTitle.setText(f.getTitle());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                progressBar.getIndeterminateDrawable().setAlpha(per);
                per-=5;
                if(per<=0){
                    progressBar.getIndeterminateDrawable().setAlpha(0);
                    timer.cancel();
                }
            }
        };
         timer.scheduleAtFixedRate(task,0,10);

        indexActivity = this;
    }

}
