package com.star.amlakonline;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

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
    public TableLayout tableLayout;
    public TextView fileCode;
    public TextView RahnPrice;
    public TextView EjarePrice;
    public TextView BuyPrice;
    public ImageButton back;
    public int per = 255;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        progressBar = findViewById(R.id.progressBar);
        back = findViewById(R.id.backBtn);
        fileTitle = findViewById(R.id.fileTitle);
        fileCode = findViewById(R.id.fileCode);
        BuyPrice = findViewById(R.id.BuyPrice);
        RahnPrice = findViewById(R.id.RahnPrice);
        EjarePrice = findViewById(R.id.EjarePrice);
        tableLayout = findViewById(R.id.detailTable);
        ConstraintLayout constraintLayout = findViewById(R.id.fileContentPlaceHolder);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);


        Gson g = new Gson();
        f =  g.fromJson(getIntent().getStringExtra("file"),File.class);

        fileTitle.setText(f.getTitle());
        fileCode.setText("کد فایل:"+f.code);
        if(f.buy != 0){
            ((ViewManager)RahnPrice.getParent()).removeView(RahnPrice);
            ((ViewManager)EjarePrice.getParent()).removeView(EjarePrice);
            constraintSet.connect(R.id.detailTable,ConstraintSet.TOP,R.id.BuyPrice,ConstraintSet.BOTTOM,0);
            constraintSet.applyTo(constraintLayout);
            BuyPrice.setText(" خرید :"+f.getPrice(f.buy));
        }
        else {
            ((ViewManager)BuyPrice.getParent()).removeView(BuyPrice);
            if (f.rahn != 0)
                RahnPrice.setText(" رهن :"+f.getPrice(f.rahn));
            if(f.ejare !=0){
                EjarePrice.setText(" اجاره :"+f.getPrice(f.ejare));
                constraintSet.connect(R.id.detailTable,ConstraintSet.TOP,R.id.EjarePrice,ConstraintSet.BOTTOM,0);
                constraintSet.applyTo(constraintLayout);
            }
            else{
                constraintSet.connect(R.id.detailTable,ConstraintSet.TOP,R.id.RahnPrice,ConstraintSet.BOTTOM,0);
                constraintSet.applyTo(constraintLayout);
                ((ViewManager)EjarePrice.getParent()).removeView(EjarePrice);
            }
        }
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
