package com.example.tgarden.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tgarden.R;

public class MainActivity extends AppCompatActivity {

    Button mWifiBtn, mLoginBtn, mRegisBtn, mSelectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWifiBtn= findViewById(R.id.wifi_btn);
        mLoginBtn= findViewById(R.id.login_btn);
        mRegisBtn= findViewById(R.id.register_btn);
        mSelectBtn= findViewById(R.id.select_btn);

        mSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LanguageActivity.class));
            }
        });

        mWifiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WifiActivity.class));
            }
        });

        mRegisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

    }
}