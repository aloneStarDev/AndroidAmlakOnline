package com.star.amlakonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.star.amlakonline.Model.File;
import com.star.amlakonline.Model.FileAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static MainActivity mainActivity;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private NavigationView navigationView;
    private DrawerLayout dl;
    private RecyclerView recyclerView;
    public static FileAdapter fileAdapter;
    private ActionBarDrawerToggle adbr;
    private ImageButton menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        if (getIntent() != null && getIntent().getBooleanExtra("fromStart", false) && StartActivity.startActivity != null)
            StartActivity.startActivity.finish();
        toolbar = findViewById(R.id.toolbar);
        dl = findViewById(R.id.drawer_layout);
        progressBar = findViewById(R.id.loadMoreProgressbar);
        menuBtn = findViewById(R.id.toolBarMenuBtn);
        recyclerView = findViewById(R.id.RecycleView);

        fileAdapter = new FileAdapter(this, new ArrayList<File>());
        try {
            File.loadMore();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(fileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        com.google.android.material.bottomnavigation.BottomNavigationItemView m = findViewById(R.id.nav_home);
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("count",fileAdapter.getItemCount()+" <------     ");
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {
//                    Toast.makeText(MainActivity.this,"scrollup",Toast.LENGTH_SHORT).show();
                } else if (dy > 0) {
                    if (!recyclerView.canScrollVertically(1)) {
                        try {
                            File.loadMore();
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    progressBar.setVisibility(View.VISIBLE);
                                }
                            });
                            TimerTask timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    });
                                }
                            };
                            Timer timer = new Timer("Timer");
                            long delay = 3000L;
                            timer.schedule(timerTask, delay);
                        } catch (Exception ex) {
                            Toast.makeText(MainActivity.this, "لطفا مجدد اتصال خود را بررسی کنید", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onClick(View v) {
                dl.openDrawer(Gravity.RIGHT, true);
            }
        });
        adbr = new ActionBarDrawerToggle(this, dl, R.string.NavbarOpen, R.string.NavbarClose);
        adbr.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(adbr);
        adbr.syncState();
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_login:
                        Toast.makeText(MainActivity.this, "login", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_register:
                        Toast.makeText(MainActivity.this, "register", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }
}
