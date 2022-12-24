package com.example.tgarden.LocationOwner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tgarden.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.FieldPosition;
import java.util.Objects;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;
import de.nitri.gauge.Gauge;
import to.freebots.temperaturegauge.TemperatureGauge;

public class RetailerMenuFragment extends Fragment {

    private ImageView imageLight;
    private ImageView imageRain;
    private ImageView imageLight1;
    private ImageView imageStatus1;
    private ImageView imageFan1;
    private ImageView imageStatus2;
    private ImageView imagePump;
    private ImageView imageStatus3;
    private ImageView imageHum1;
    private ImageView imageStatus4;


    private TextView lightText;
    private TextView rainText;
    private TextView textTemp;

    private PieView pieView_hum;
    private PieView pieView_soil;
    private Gauge tempGauge;

    float MIN_TEMP = 0;
    private int MAX_TEMP = 150;

    public RetailerMenuFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retailer_menu, container, false);

        tempGauge = view.findViewById(R.id.gauge);
        lightText = view.findViewById(R.id.anhsang);
        rainText = view.findViewById(R.id.rain);
        pieView_hum = view.findViewById(R.id.pieView_hum);
        pieView_soil = view.findViewById(R.id.pieView_soil);
        textTemp = view.findViewById(R.id.textTemp);

        imageLight = view.findViewById(R.id.imagelight);
        imageRain = view.findViewById(R.id.image_rain);

        imageLight1 = view.findViewById(R.id.light1);
        imageStatus1 = view.findViewById(R.id.light1_status);
        imageFan1 = view.findViewById(R.id.fan);
        imageStatus2 = view.findViewById(R.id.fan_status);

        imagePump = view.findViewById(R.id.pump1);
        imageStatus3 = view.findViewById(R.id.pump_status);
        imageHum1 = view.findViewById(R.id.hum);
        imageStatus4 = view.findViewById(R.id.hum_status);

        Firebase.setAndroidContext(requireContext());

        final Firebase mRefTemp = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/dht22/temp");
        Firebase mRefLight = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/lights");
        Firebase mRefHum = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/dht22/hum");
        Firebase mRefSoil = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/soil");
        Firebase mRefRain = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/rain");

        Firebase btnLight1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/Auto/lamp");
        Firebase btnFan = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/Auto/fan");
        Firebase btnPump = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/Auto/pump");
        Firebase btnHum = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/Auto/hum");

        pieView_hum.setPercentageBackgroundColor(getResources().getColor(R.color.greennhat));
        //        pieView_hum.setMainBackgroundColor(getResources().getColor(R.color.white));

        pieView_soil.setPercentageBackgroundColor(getResources().getColor(R.color.greennhat));
        //        pieView_soil.setMainBackgroundColor(getResources().getColor(R.color.white));


        btnHum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                if (value == 1) {
                    imageHum1.setImageResource(R.drawable.humidifier);
                    imageStatus4.setImageResource(R.drawable.switchon1);
                } else if (value == 2) {
                    imageHum1.setImageResource(R.drawable.humidifier_off);
                    imageStatus4.setImageResource(R.drawable.switchoff1);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        btnPump.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                if (value == 1) {
                    imagePump.setImageResource(R.drawable.pump2);
                    imageStatus3.setImageResource(R.drawable.switchon1);
                } else if (value == 2) {
                    imagePump.setImageResource(R.drawable.pumpoff);
                    imageStatus3.setImageResource(R.drawable.switchoff1);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        btnLight1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                if (value == 1) {
                    imageLight1.setImageResource(R.drawable.lighton);
                    imageStatus1.setImageResource(R.drawable.switchon1);
                } else if (value == 2) {
                    imageLight1.setImageResource(R.drawable.lightoff);
                    imageStatus1.setImageResource(R.drawable.switchoff1);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        btnFan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                if (value == 1) {
                    imageFan1.setImageResource(R.drawable.fan);
                    imageStatus2.setImageResource(R.drawable.switchon1);
                } else if (value == 2) {
                    imageFan1.setImageResource(R.drawable.fanoff);
                    imageStatus2.setImageResource(R.drawable.switchoff1);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRefHum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                float i = Float.parseFloat(value);
                pieView_hum.setPercentage(i);
                pieView_hum.setPieInnerPadding(30);
                pieView_hum.setPercentageTextSize(20);
                PieAngleAnimation animation = new PieAngleAnimation(pieView_hum);
                animation.setDuration(2000);
                pieView_hum.startAnimation(animation);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mRefSoil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                float i = Float.parseFloat(value);
                pieView_soil.setPieInnerPadding(30);
                pieView_soil.setPercentageTextSize(20);
                pieView_soil.setPercentage(i);
                PieAngleAnimation animation = new PieAngleAnimation(pieView_soil);
                animation.setDuration(2000); //This is the duration of the animation in millis
                pieView_soil.startAnimation(animation);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRefRain.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1")) {
                    rainText.setText(R.string.have_rain);
                    imageRain.setImageResource(R.drawable.rain);
                } else if (value.equals("0")) {
                    rainText.setText(R.string.no_rain);
                    imageRain.setImageResource(R.drawable.sun);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRefLight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                int i = Integer.parseInt(value);
                if (i < 6) {
                    lightText.setText(R.string.dark);
                    imageLight.setImageResource(R.drawable.moon);
                } else if (i > 5 & i < 100) {
                    lightText.setText(R.string.cloudy);
                    imageLight.setImageResource(R.drawable.cloudy);
                } else {
                    lightText.setText(R.string.sunny);
                    imageLight.setImageResource(R.drawable.sunny);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        mRefTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Float value = dataSnapshot.getValue(Float.class);
                textTemp.setText(value + "°C");
                tempGauge.moveToValue(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return view;
    }


}