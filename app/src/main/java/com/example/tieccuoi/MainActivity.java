package com.example.tieccuoi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tieccuoi.fragment.HomeFragment;
import com.example.tieccuoi.fragment.InfoFragment;
import com.example.tieccuoi.fragment.ManageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView mnBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mnBottom = findViewById(R.id.navmenu);

        mnBottom.setOnItemSelectedListener(getListener());
        loadFragment(new HomeFragment());


        ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                if (item.getItemId() == R.id.mnHome) {
                    loadFragment(new HomeFragment());
                    return true;
                } else if (item.getItemId() == R.id.mnManage) {
                    loadFragment(new ManageFragment());
                    return true;
                } else if (item.getItemId() == R.id.mnInfo) {
                    loadFragment(new InfoFragment());
                    return true;
                }
                return true;
            }
        };
    }



    void loadFragment(Fragment fragment){
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_fragment, fragment);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }


}