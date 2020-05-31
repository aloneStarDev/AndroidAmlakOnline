package com.star.amlakonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.star.amlakonline.ApiConnection.ApiConnection;
import com.star.amlakonline.Model.File;
import com.star.amlakonline.Model.FileAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout dl;
    private RecyclerView  recyclerView;
    private FileAdapter fileAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ActionBarDrawerToggle adbr;
    private ImageButton menuBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        dl = findViewById(R.id.drawer_layout);
        menuBtn = findViewById(R.id.toolBarMenuBtn);
        recyclerView = findViewById(R.id.RecycleView);
        ArrayList<File> files = new ArrayList<File>();
        files.add(new File(1000));
        files.add(new File(2000));
        files.add(new File(3000));
        files.add(new File(4000));
        files.add(new File(5000));
        files.add(new File(6000));
        fileAdapter = new FileAdapter(this,files);
        recyclerView.setAdapter(fileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {
                    Toast.makeText(MainActivity.this,"scrollup",Toast.LENGTH_SHORT).show();
                } else if (dy > 0) {

                    Toast.makeText(MainActivity.this,"scrolldown",Toast.LENGTH_SHORT).show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RtlHardcoded")
            @Override
            public void onClick(View v) {
                dl.openDrawer(Gravity.RIGHT,true);
            }
        });
        adbr = new ActionBarDrawerToggle(this,dl,R.string.NavbarOpen,R.string.NavbarClose);
        adbr.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(adbr);
        adbr.syncState();
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.nav_login:
                //hey sepeher you can drop this
//                            ApiConnection apiConnection = new ApiConnection();
//                            apiConnection.execute("http://amlakonlin.ir/api/profile","ApiToken=z9csfDrF5qCEByhsEpjALMG8PRUNbIMcwXFK95yZCpRvHAE67qewExU4xmIp");
                Toast.makeText(MainActivity.this, "login",Toast.LENGTH_SHORT).show();break;
            case R.id.nav_register:
                Toast.makeText(MainActivity.this, "register",Toast.LENGTH_SHORT).show();break;
            default:
                return true;
        }
        return true;
                }
        });
    }
}
