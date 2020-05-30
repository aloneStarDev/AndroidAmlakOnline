package com.star.amlakonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.star.amlakonline.ApiConnection.ApiConnection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle adbr;
    private ImageButton menuBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        dl = (DrawerLayout)findViewById(R.id.drawer_layout);
        menuBtn = (ImageButton) findViewById(R.id.toolBarMenuBtn);
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
