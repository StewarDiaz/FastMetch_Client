package com.programmingjd.fastmetch_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;

import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment fragmentService, fragmentMap, fragmentprofile;
    String UserId;

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        UserId = intent.getStringExtra("IdToUseC");
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragmentService = new ServiceClientFragment();
        fragmentMap = new MapsClientFragment();
        //fragmentprofile = new MercadoPagoIntegerFragment();

        //getSupportFragmentManager().beginTransaction().add(R.id.containerFragments, fragmentMap).commit();

        bottomNav = findViewById(R.id.btnNavView);
        bottomNav.setOnNavigationItemSelectedListener(mOnNav);
    }

   private BottomNavigationView.OnNavigationItemSelectedListener mOnNav = new BottomNavigationView.OnNavigationItemSelectedListener() {
       @Override
       public boolean onNavigationItemSelected(@NonNull MenuItem item) {

           transaction = getSupportFragmentManager().beginTransaction();

           switch (item.getItemId()){
               case R.id.profile:
                   Intent iProfile = new Intent(getApplicationContext(), ProfileClient.class);
                   iProfile.putExtra("IdToUseClient", UserId);
                   startActivity(iProfile);
                   break;
               case R.id.Service:
//                   transaction.replace(R.id.containerFragments, fragmentMap).commit();
                   Intent iMap = new Intent(getApplicationContext(), TheServiceClient.class);
                   startActivity(iMap);
                   break;
               case R.id.Location:
                   transaction.replace(R.id.containerFragments, fragmentMap).commit();
                   break;
           }
           return false;
       }
   };
}