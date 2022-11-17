package com.example.tgarden.LocationOwner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.tgarden.R;
import com.google.android.material.chip.Chip;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class RetailerDashboard extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_retailer_dashboard);

        chipNavigationBar = findViewById(R.id.bottom_chip_nav);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_home, true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RetailerMenuFragment()).commit();
        bottomMenu();
    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.bottom_nav_home:
                        fragment = new RetailerMenuFragment();
                        break;
                    case R.id.bottom_nav_setting:
                        fragment = new RetailerSettingFragment();
                        break;
                    case R.id.bottom_nav_data:
                        fragment = new RetailerDataFragment();
                        break;
                    case R.id.bottom_nav_account:
                        fragment = new RetailerAccountFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }

            });
        }
    }