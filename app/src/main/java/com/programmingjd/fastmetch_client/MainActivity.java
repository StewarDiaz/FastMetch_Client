package com.programmingjd.fastmetch_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bottomNav = findViewById(R.id.btnNavView);


        bottomNav.setOnNavigationItemSelectedListener(mOnNav);
    }

   private BottomNavigationView.OnNavigationItemSelectedListener mOnNav = new BottomNavigationView.OnNavigationItemSelectedListener() {
       @Override
       public boolean onNavigationItemSelected(@NonNull MenuItem item) {

           switch (item.getItemId()){
               case R.id.task:
                   Intent iTask = new Intent(getApplicationContext(), DocenteIndex.class);
                   startActivity(iTask);
                   break;
               case R.id.location:
                   Intent iLocat = new Intent(getApplicationContext(), LocationMapClient.class);
                   startActivity(iLocat);
                   return true;
               case R.id.profile:
                   Intent iProf = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(iProf);
                   return true;

           }
           return false;
       }
   };
}