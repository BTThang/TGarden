package com.example.tgarden.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tgarden.R;
import com.google.rpc.context.AttributeContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.grpc.internal.SharedResourceHolder;

public class LanguageActivity extends AppCompatActivity {

    Spinner spinner;
    Locale myLoCale;
    String currentLanguage = "Eng", currentLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        currentLanguage = getIntent().getStringExtra(currentLang);
        spinner = (Spinner) findViewById(R.id.spinner);

        List<String> list = new ArrayList<>();

        list.add("Select Language");
        list.add("English");
        list.add("Tiếng Việt");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, com.chaos.view.R.layout.support_simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("vi");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setLocale(String localeName){
        if(!localeName.equals(currentLanguage)){
            myLoCale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLoCale;
            res.updateConfiguration(conf, dm);

            Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        }else{
            Toast.makeText(LanguageActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}