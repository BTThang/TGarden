package com.example.tgarden.LocationOwner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.tgarden.R;
import com.example.tgarden.User.MainActivity;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.protobuf.StringValue;

import org.checkerframework.checker.units.qual.A;

public class RetailerSettingFragment extends Fragment {

    boolean check = false;

    private boolean anhsang_high = true;
    private boolean anhsang_low = true;
    private boolean nhietdo_high = true;
    private boolean nhietdo_low = true;

    private boolean doamkhi_low = true;
    private boolean doamkhi_high = true;
    private boolean doamdat_low = true;
    private boolean doamdat_high = true;

    private boolean lampCheck = true;
    private boolean fanCheck = true;
    private boolean pumpCheck = true;
    private boolean mistCheck = true;

    public RetailerSettingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retailer_setting, container, false);

        final SeekBar seekTemp = view.findViewById(R.id.seekBar1);
        final SeekBar seekLights = view.findViewById(R.id.seekBar2);
        final SeekBar seekHum = view.findViewById(R.id.seekBar3);
        final SeekBar seekSoil = view.findViewById(R.id.seekBar4);

//        final SeekBar seekTempoff = view.findViewById(R.id.seekBar11);
//        final SeekBar seekLightsoff = view.findViewById(R.id.seekBar22);
//        final SeekBar seekHumoff = view.findViewById(R.id.seekBar33);
//        final SeekBar seekSoiloff = view.findViewById(R.id.seekBar44);

        //Set alarm Lamp1
        final EditText hourLamp1 = view.findViewById(R.id.lamphourlan1);
        final EditText minLamp1 = view.findViewById(R.id.lampmintuelan1);
        final EditText timeoutLamp1 = view.findViewById(R.id.timeout_lamp1);
        final Button setLamp1 = view.findViewById(R.id.btnsetlamplan1);
        final Switch swLamp1 = view.findViewById(R.id.swlamplan1);

        //        Set alarm Lamp2
        final EditText hourLamp2 = view.findViewById(R.id.lamphourlan2);
        final EditText minLamp2 = view.findViewById(R.id.lampmintuelan2);
        final EditText timeoutLamp2 = view.findViewById(R.id.timeout_lamp2);
        final Button setLamp2 = view.findViewById(R.id.btnsetlamplan2);
        final Switch swLamp2 = view.findViewById(R.id.swlamplan2);


        //        Set alarm Fan1
        final EditText hourFan1 = view.findViewById(R.id.fanhourlan1);
        final EditText minFan1 = view.findViewById(R.id.fanmintuelan1);
        final EditText timeoutFan1 = view.findViewById(R.id.timeout_fan1);
        final Button setFan1 = view.findViewById(R.id.btnsetfanlan1);
        final Switch swFan1 = view.findViewById(R.id.swfanlan1);

        //        Set alarm Fan2
        final EditText hourFan2 = view.findViewById(R.id.fanhourlan2);
        final EditText minFan2 = view.findViewById(R.id.fanmintuelan2);
        final EditText timeoutFan2 = view.findViewById(R.id.timeout_fan2);
        final Button setFan2 = view.findViewById(R.id.btnsetfanlan2);
        final Switch swFan2 = view.findViewById(R.id.swfanlan2);

        //        Set alarm Pump1
        final EditText hourPump1 = view.findViewById(R.id.pumphourlan1);
        final EditText minPump1 = view.findViewById(R.id.pumpmintuelan1);
        final EditText timeoutPump1 = view.findViewById(R.id.timeout_pump1);
        final Button setPump1 = view.findViewById(R.id.btnsetpumplan1);
        final Switch swPump1 = view.findViewById(R.id.swpumplan1);


        //        Set alarm Pump2
        final EditText hourPump2 = view.findViewById(R.id.pumphourlan2);
        final EditText minPump2 = view.findViewById(R.id.pumpmintuelan2);
        final EditText timeoutPump2 = view.findViewById(R.id.timeout_pump2);
        final Button setPump2 = view.findViewById(R.id.btnsetpumplan2);
        final Switch swPump2 = view.findViewById(R.id.swpumplan2);

        //        Set alarm Humidifier1
        final EditText hourHum1 = view.findViewById(R.id.humhourlan1);
        final EditText minHum1 = view.findViewById(R.id.hummintuelan1);
        final EditText timeoutHum1 = view.findViewById(R.id.timeout_hum1);
        final Button setHum1 = view.findViewById(R.id.btnsethumlan1);
        final Switch swHum1 = view.findViewById(R.id.swhumlan1);

        //        Set alarm Humidifier2
        final EditText hourHum2 = view.findViewById(R.id.humhourlan2);
        final EditText minHum2 = view.findViewById(R.id.hummintuelan2);
        final EditText timeoutHum2 = view.findViewById(R.id.timeout_hum2);
        final Button setHum2 = view.findViewById(R.id.btnsethumlan2);
        final Switch swHum2 = view.findViewById(R.id.swhumlan2);


//        Set TextView Manual
        final TextView maLamp = view.findViewById(R.id.den);
        final TextView maFan = view.findViewById(R.id.quat);
        final TextView maPump = view.findViewById(R.id.bom);
        final TextView maHumi = view.findViewById(R.id.suong);

//        Set ImageView Manual

        final ImageView imageLamp = view.findViewById(R.id.imageView1);
        final ImageView imageFan = view.findViewById(R.id.imageView2);
        final ImageView imagePump = view.findViewById(R.id.imageView3);
        final ImageView imageHum = view.findViewById(R.id.imageView4);

//        Set TextView Automation

        final TextView textTemp = view.findViewById(R.id.text1);
        final TextView textLights = view.findViewById(R.id.text2);
        final TextView textHum = view.findViewById(R.id.text3);
        final TextView textSoil = view.findViewById(R.id.text4);

//        final TextView textTempoff = view.findViewById(R.id.text11);
//        final TextView textLightsoff = view.findViewById(R.id.text22);
//        final TextView textHumoff = view.findViewById(R.id.text33);
//        final TextView textSoiloff = view.findViewById(R.id.text44);

//        Set Mode

        final Switch autoSwitch = view.findViewById(R.id.switch_autoMode);
        final Switch manualSwitch = view.findViewById(R.id.switch_ManualMode);
        final Switch alarmSwitch = view.findViewById(R.id.switch_alarm);

//        Set Manual Switch

        final Switch lampSwitch = view.findViewById(R.id.switch1);
        final Switch fanSwitch = view.findViewById(R.id.switch2);
        final Switch pumpSwitch = view.findViewById(R.id.switch3);
        final Switch HumSwitch = view.findViewById(R.id.switch4);

//        Set Switch Firebase

        final Firebase mReSwLamp = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/RELAY1/S1_Lamp");
        final Firebase mReSwFan = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/RELAY2/S2_Fan");
        final Firebase mReSwPump = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/RELAY3/S3_Pump");
        final Firebase mReSwHum = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/RELAY4/S4_Hum");
//        Set Mode Firebase

        final Firebase mSwAuto = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/S1_Auto");
        final Firebase mSwManual = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/S1_Manual");
        final Firebase mSwAlarm = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Status");

//        Set Firebase Auto

        final Firebase mAutoTempH = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Auto/Temp_high");
//        final Firebase mAutoTempL = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Auto/Temp_low");
        final Firebase mAutoHum = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Auto/Hum");
        final Firebase mAutoSoil = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Auto/Soil");
        final Firebase mAutoLights = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Auto/Lights");

//        final Firebase mAutoHumOff = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Auto/Hum_off");
//        final Firebase mAutoSoilOff = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Auto/Soil_off");
//        final Firebase mAutoLightsOff = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Auto/Lights_off");

//        Read Firebase sensor
        final Firebase mReHum = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/dht22/hum");
        final Firebase mReTemp = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/dht22/temp");
        final Firebase mReLights = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/lights");
        final Firebase mReSoil = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/soil");
        final Firebase mRain = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/sensor/rain");

//        Link Firebase Pump 1
        final Firebase mHourPump1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Pump/AmPump1/Hour");
        final Firebase mMinutePump1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Pump/AmPump1/Minutes");
        final Firebase mAcPump1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Pump/AmPump1/Activity");
        final Firebase mStatusPump1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Pump/AmPump1/Status");
//        Link Firebase Pump 2
        final Firebase mHourPump2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Pump/AmPump2/Hour");
        final Firebase mMinutePump2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Pump/AmPump2/Minutes");
        final Firebase mAcPump2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Pump/AmPump2/Activity");
        final Firebase mStatusPump2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Pump/AmPump2/Status");

//        Link Firebase Lamp 1
        final Firebase mHourLamp1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Lamp/AmLamp1/Hour");
        final Firebase mMinuteLamp1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Lamp/AmLamp1/Minutes");
        final Firebase mAcLamp1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Lamp/AmLamp1/Activity");
        final Firebase mStatusLamp1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Lamp/AmLamp1/Status");
//        Link Firebase Lamp 2
        final Firebase mHourLamp2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Lamp/AmLamp2/Hour");
        final Firebase mMinuteLamp2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Lamp/AmLamp2/Minutes");
        final Firebase mAcLamp2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Lamp/AmLamp2/Activity");
        final Firebase mStatusLamp2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Lamp/AmLamp2/Status");

        //        Link Firebase Fan 1
        final Firebase mHourFan1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Fan/AmFan1/Hour");
        final Firebase mMinuteFan1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Fan/AmFan1/Minutes");
        final Firebase mAcFan1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Fan/AmFan1/Activity");
        final Firebase mStatusFan1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Fan/AmFan1/Status");
//        Link Firebase Fan 2
        final Firebase mHourFan2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Fan/AmFan2/Hour");
        final Firebase mMinuteFan2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Fan/AmFan2/Minutes");
        final Firebase mAcFan2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Fan/AmFan2/Activity");
        final Firebase mStatusFan2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Fan/AmFan2/Status");

//        Link Firebase Humidifier 1
        final Firebase mHourHum1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Humidifier/AmHum1/Hour");
        final Firebase mMinuteHum1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Humidifier/AmHum1/Minutes");
        final Firebase mAcHum1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Humidifier/AmHum1/Activity");
        final Firebase mStatusHum1 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Humidifier/AmHum1/Status");
//        Link Firebase Humidifier 2
        final Firebase mHourHum2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Humidifier/AmHum2/Hour");
        final Firebase mMinuteHum2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Humidifier/AmHum2/Minutes");
        final Firebase mAcHum2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Humidifier/AmHum2/Activity");
        final Firebase mStatusHum2 = new Firebase("https://tgarden-f7710-default-rtdb.firebaseio.com/TGarden/Alarm/Humidifier/AmHum2/Status");


//        Set Humidifier 2 System------------------------------------------------------------------------------------------
        mStatusHum2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1")) {
                    swHum2.setChecked(true);
                } else if (value.equals("0")) {
                    swHum2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mAcHum2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                timeoutHum2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mMinuteHum2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                minHum2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mHourHum2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                hourHum2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        setHum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference humHour2 = database.getReference("TGarden/Alarm/Humidifier/AmHum2/Hour");
                DatabaseReference humMinutes2 = database.getReference("TGarden/Alarm/Humidifier/AmHum2/Minutes");
                DatabaseReference humAc2 = database.getReference("TGarden/Alarm/Humidifier/AmHum2/Activity");

                humHour2.setValue(hourHum2.getText().toString().trim());

                humMinutes2.setValue(minHum2.getText().toString().trim());
                humAc2.setValue(timeoutHum2.getText().toString().trim());
            }
        });

        swHum2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staHum2 = database.getReference("TGarden/Alarm/Humidifier/AmHum2/Status");
                    staHum2.setValue("1");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staHum2 = database.getReference("TGarden/Alarm/Humidifier/AmHum2/Status");
                    staHum2.setValue("0");
                }
            }
        });


        //        Set Humidifier 1 System------------------------------------------------------------------------------------------
        mStatusHum1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1")) {
                    swHum1.setChecked(true);
                } else if (value.equals("0")) {
                    swHum1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mAcHum1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                timeoutHum1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mMinuteHum1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                minHum1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mHourHum1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                hourHum1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        setHum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference humHour1 = database.getReference("TGarden/Alarm/Humidifier/AmHum1/Hour");
                DatabaseReference humMinutes1 = database.getReference("TGarden/Alarm/Humidifier/AmHum1/Minutes");
                DatabaseReference humAc1 = database.getReference("TGarden/Alarm/Humidifier/AmHum1/Activity");
                humHour1.setValue(hourHum1.getText().toString().trim());

                humMinutes1.setValue(minHum1.getText().toString().trim());
                humAc1.setValue(timeoutHum1.getText().toString().trim());
            }
        });

        swHum1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staHum1 = database.getReference("TGarden/Alarm/Humidifier/AmHum1/Status");
                    staHum1.setValue("1");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staHum1 = database.getReference("TGarden/Alarm/Humidifier/AmHum1/Status");
                    staHum1.setValue("0");
                }
            }
        });


        //        Set Pump 2 System------------------------------------------------------------------------------------------
        mStatusPump2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1")) {
                    swPump2.setChecked(true);
                } else if (value.equals("0")) {
                    swPump2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mAcPump2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                timeoutPump2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mMinutePump2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                minPump2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mHourPump2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                hourPump2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        setPump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference pumpHour2 = database.getReference("TGarden/Alarm/Pump/AmPump2/Hour");
                DatabaseReference pumpMinutes2 = database.getReference("TGarden/Alarm/Pump/AmPump2/Minutes");
                DatabaseReference pumpAc2 = database.getReference("TGarden/Alarm/Pump/AmPump2/Activity");

                pumpHour2.setValue(hourPump2.getText().toString().trim());

                pumpMinutes2.setValue(minPump2.getText().toString().trim());

                pumpAc2.setValue(timeoutPump2.getText().toString().trim());
            }
        });

        swPump2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staPump2 = database.getReference("TGarden/Alarm/Pump/AmPump2/Status");
                    staPump2.setValue("1");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staPump2 = database.getReference("TGarden/Alarm/Pump/AmPump2/Status");
                    staPump2.setValue("0");
                }
            }
        });


        //        Set Pump 1 System------------------------------------------------------------------------------------------
        mStatusPump1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1")) {
                    swPump1.setChecked(true);
                } else if (value.equals("0")) {
                    swPump1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        mAcPump1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                timeoutPump1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mMinutePump1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                minPump1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mHourPump1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                hourPump1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        setPump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference pumpHour1 = database.getReference("TGarden/Alarm/Pump/AmPump1/Hour");
                DatabaseReference pumpMinutes1 = database.getReference("TGarden/Alarm/Pump/AmPump1/Minutes");
                DatabaseReference pumpAc1 = database.getReference("TGarden/Alarm/Pump/AmPump1/Activity");

                pumpHour1.setValue(hourPump1.getText().toString().trim());

                pumpMinutes1.setValue(minPump1.getText().toString().trim());

                pumpAc1.setValue(timeoutPump1.getText().toString().trim());
            }
        });

        swPump1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staPump1 = database.getReference("TGarden/Alarm/Pump/AmPump1/Status");
                    staPump1.setValue("1");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staPump1 = database.getReference("TGarden/Alarm/Pump/AmPump1/Status");
                    staPump1.setValue("0");
                }
            }
        });


        //        Set Fan 2 System------------------------------------------------------------------------------------------
        mStatusFan2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1")) {
                    swFan2.setChecked(true);
                } else if (value.equals("0")) {
                    swFan2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        mAcFan2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                timeoutFan2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mMinuteFan2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                minFan2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mHourFan2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                hourFan2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        setFan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference fanHour2 = database.getReference("TGarden/Alarm/Fan/AmFan2/Hour");
                DatabaseReference fanMinutes2 = database.getReference("TGarden/Alarm/Fan/AmFan2/Minutes");
                DatabaseReference fanAc2 = database.getReference("TGarden/Alarm/Fan/AmFan2/Activity");
                fanHour2.setValue(hourFan2.getText().toString().trim());

                fanMinutes2.setValue(minFan2.getText().toString().trim());
                fanAc2.setValue(timeoutFan2.getText().toString().trim());
            }
        });

        swFan2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staFan2 = database.getReference("TGarden/Alarm/Fan/AmFan2/Status");
                    staFan2.setValue("1");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staFan2 = database.getReference("TGarden/Alarm/Fan/AmFan2/Status");
                    staFan2.setValue("0");
                }
            }
        });

        //        Set Fan 1 System------------------------------------------------------------------------------------------
        mStatusFan1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1")) {
                    swFan1.setChecked(true);
                } else if (value.equals("0")) {
                    swFan1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        mAcFan1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                timeoutFan1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mMinuteFan1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                minFan1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mHourFan1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                hourFan1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        setFan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference fanHour1 = database.getReference("TGarden/Alarm/Fan/AmFan1/Hour");
                DatabaseReference fanMinutes1 = database.getReference("TGarden/Alarm/Fan/AmFan1/Minutes");
                DatabaseReference fanAc1 = database.getReference("TGarden/Alarm/Fan/AmFan1/Activity");
                fanHour1.setValue(hourFan1.getText().toString().trim());
                fanMinutes1.setValue(minFan1.getText().toString().trim());
                fanAc1.setValue(timeoutFan1.getText().toString().trim());
            }
        });

        swFan1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staFan1 = database.getReference("TGarden/Alarm/Fan/AmFan1/Status");
                    staFan1.setValue("1");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staFan1 = database.getReference("TGarden/Alarm/Fan/AmFan1/Status");
                    staFan1.setValue("0");
                }
            }
        });

        //        Set Lamp 2 System------------------------------------------------------------------------------------------
        mStatusLamp2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1")) {
                    swLamp2.setChecked(true);
                } else if (value.equals("0")) {
                    swLamp2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        mAcLamp2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                timeoutLamp2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mMinuteLamp2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                minLamp2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mHourLamp2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                hourLamp2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        setLamp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference lampHour2 = database.getReference("TGarden/Alarm/Lamp/AmLamp2/Hour");
                DatabaseReference lampMinutes2 = database.getReference("TGarden/Alarm/Lamp/AmLamp2/Minutes");
                DatabaseReference lampAc2 = database.getReference("TGarden/Alarm/Lamp/AmLamp2/Activity");
                lampHour2.setValue(hourLamp2.getText().toString().trim());
                lampMinutes2.setValue(minLamp2.getText().toString().trim());
                lampAc2.setValue(timeoutLamp2.getText().toString().trim());
            }
        });

        swLamp2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staLamp2 = database.getReference("TGarden/Alarm/Lamp/AmLamp2/Status");
                    staLamp2.setValue("1");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staLamp2 = database.getReference("TGarden/Alarm/Lamp/AmLamp2/Status");
                    staLamp2.setValue("0");
                }
            }
        });


        //        Set Lamp 1 System------------------------------------------------------------------------------------------
        mStatusLamp1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("1")) {
                    swLamp1.setChecked(true);
                } else if (value.equals("0")) {
                    swLamp1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mAcLamp1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                timeoutLamp1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mMinuteLamp1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                minLamp1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mHourLamp1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                hourLamp1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        setLamp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference lampHour1 = database.getReference("TGarden/Alarm/Lamp/AmLamp1/Hour");
                DatabaseReference lampMinutes1 = database.getReference("TGarden/Alarm/Lamp/AmLamp1/Minutes");
                DatabaseReference lampActivity = database.getReference("TGarden/Alarm/Lamp/AmLamp1/Activity");

                lampHour1.setValue(hourLamp1.getText().toString().trim());
                lampMinutes1.setValue(minLamp1.getText().toString().trim());
                lampActivity.setValue(timeoutLamp1.getText().toString().trim());
            }
        });

        swLamp1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staLamp1 = database.getReference("TGarden/Alarm/Lamp/AmLamp1/Status");
                    staLamp1.setValue("1");
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference staLamp1 = database.getReference("TGarden/Alarm/Lamp/AmLamp1/Status");
                    staLamp1.setValue("0");
                }
            }
        });


//        +++++++++++++++++++++++++++++++++++++++++++++++++++++++Setting status Manual Mode+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //Set Lamp manual***************************************************************************************************************
        mReSwLamp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int Value = dataSnapshot.getValue(int.class);
//                if (lampCheck) {
                if (Value == 1) {
                    lampSwitch.setChecked(true);
                } else if (Value == 2) {
                    lampSwitch.setChecked(false);
                }
//                    lampCheck = false;
//                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        mReLights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                maLamp.setText("Lights: " + value + "lux");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        lampSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TransitionDrawable drawable = (TransitionDrawable) imageLamp.getDrawable();

                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myLamp = database.getReference("RELAY1/S1_Lamp");
                    myLamp.setValue(1);
                    drawable.startTransition(800);
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myLamp = database.getReference("RELAY1/S1_Lamp");
                    myLamp.setValue(2);
                    drawable.reverseTransition(200);
                }
            }
        });


        //Set Fan manual***************************************************************************************************************

        mReSwFan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int Value = dataSnapshot.getValue(int.class);
//                if (fanCheck) {
                if (Value == 1) {
                    fanSwitch.setChecked(true);
                } else if (Value == 2) {
                    fanSwitch.setChecked(false);
                }
//                    fanCheck = false;

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mReTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                maFan.setText("Temp: " + value + "°C");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        fanSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TransitionDrawable drawable = (TransitionDrawable) imageFan.getDrawable();

                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myFan = database.getReference("RELAY2/S2_Fan");
                    myFan.setValue(1);
                    drawable.startTransition(800);
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myFan = database.getReference("RELAY2/S2_Fan");
                    myFan.setValue(2);
                    drawable.reverseTransition(200);
                }
            }
        });


        //Set Pump manual***************************************************************************************************************
        mReSwPump.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int Value = dataSnapshot.getValue(int.class);
//                if (pumpCheck) {
                if (Value == 1) {
                    pumpSwitch.setChecked(true);
                } else if (Value == 2) {
                    pumpSwitch.setChecked(false);
                }
//                    pumpCheck = false;
//                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        mReSoil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                maPump.setText("Soil: " + value + "%");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        pumpSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TransitionDrawable drawable = (TransitionDrawable) imagePump.getDrawable();

                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myPump = database.getReference("RELAY3/S3_Pump");
                    myPump.setValue(1);
                    drawable.startTransition(800);
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference pump = database.getReference("RELAY3/S3_Pump");
                    pump.setValue(2);
                    drawable.reverseTransition(200);
                }
            }
        });

        //Set Humidifier manual***************************************************************************************************************
        mReSwHum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int Value = dataSnapshot.getValue(int.class);
//                if (mistCheck) {
                if (Value == 1) {
                    HumSwitch.setChecked(true);
                } else if (Value == 2) {
                    HumSwitch.setChecked(false);
                }
//                    mistCheck = false;
//                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        mReHum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                maHumi.setText("Hum: " + value + "%");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        HumSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TransitionDrawable drawable = (TransitionDrawable) imageHum.getDrawable();

                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference hum = database.getReference("RELAY4/S4_Hum");
                    hum.setValue(1);
                    drawable.startTransition(800);
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference hum = database.getReference("RELAY4/S4_Hum");
                    hum.setValue(2);
                    drawable.reverseTransition(200);
                }
            }
        });


//        ++++++++++++++++++++++++++++++++++++++++++++++++++++Setting Auto Mode++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


//        Setting auto Fan
        mAutoTempH.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);

//                int i = Integer.parseInt(value.replaceAll("[\\D]", ""));
                if (nhietdo_high) {
                    seekTemp.setProgress(value);
                    nhietdo_high = false;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        seekTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textTemp.setText(progress + "°C");
                String temp = String.valueOf(progress);
                int i = Integer.parseInt(temp);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference highTemp = database.getReference("TGarden/Auto/Temp_high");
                highTemp.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //        Setting auto  Fan off
//        mAutoTempL.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int value = dataSnapshot.getValue(int.class);
//
////                int i = Integer.parseInt(value.replaceAll("[\\D]", ""));
//                if (nhietdo_low) {
//                    seekTempoff.setProgress(value);
//                    nhietdo_low = false;
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        seekTempoff.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                textTempoff.setText(progress + "°C");
//                String temp = String.valueOf(progress);
//                int i = Integer.parseInt(temp);
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference highTemp = database.getReference("TGarden/Auto/Temp_low");
//                highTemp.setValue(i);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


        //        Setting auto Lamp On
        mAutoLights.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);

//                int i = Integer.parseInt(value.replaceAll("[\\D]", ""));
                if (anhsang_high) {
                    seekLights.setProgress(value);
                    anhsang_high = false;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        seekLights.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textLights.setText(progress + "lux");
                String lights = String.valueOf(progress);
                int i = Integer.parseInt(lights);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference highlights = database.getReference("TGarden/Auto/Lights");
                highlights.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        //        Setting auto Lamp OFF
//        mAutoLightsOff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int value = dataSnapshot.getValue(int.class);
//
////                int i = Integer.parseInt(value.replaceAll("[\\D]", ""));
//                if (anhsang_low) {
//                    seekLightsoff.setProgress(value);
//                    anhsang_low = false;
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        seekLightsoff.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                textLightsoff.setText(progress + "lux");
//                String lights = String.valueOf(progress);
//                int i = Integer.parseInt(lights);
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference highlights = database.getReference("TGarden/Auto/Lights_off");
//                highlights.setValue(i);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


        //        Setting auto Humidifier On
        mAutoHum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);

//                int i = Integer.parseInt(value.replaceAll("[\\D]", ""));
                if (doamkhi_low) {
                    seekHum.setProgress(value);
                    doamkhi_low = false;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        seekHum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textHum.setText(progress + "%");
                String hum = String.valueOf(progress);
                int i = Integer.parseInt(hum);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference highHum = database.getReference("TGarden/Auto/Hum");
                highHum.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        //        Setting auto Humidifier Off
//        mAutoHumOff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int value = dataSnapshot.getValue(int.class);
//
////                int i = Integer.parseInt(value.replaceAll("[\\D]", ""));
//                if (doamkhi_high) {
//                    seekHumoff.setProgress(value);
//                    doamkhi_high = false;
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        seekHumoff.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                textHumoff.setText(progress + "%");
//                String hum = String.valueOf(progress);
//                int i = Integer.parseInt(hum);
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference highHum = database.getReference("TGarden/Auto/Hum_off");
//                highHum.setValue(i);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


        //        Setting auto Pump On
        mAutoSoil.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
//                int i = Integer.parseInt(value.replaceAll("[\\D]", ""));
                if (doamdat_low) {
                    seekSoil.setProgress(value);
                    doamdat_low = false;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        seekSoil.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSoil.setText(progress + "%");
                String soil = String.valueOf(progress);
                int i = Integer.parseInt(soil);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference highSoil = database.getReference("TGarden/Auto/Soil");
                highSoil.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //        Setting auto Pump Off
//        mAutoSoilOff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int value = dataSnapshot.getValue(int.class);
////                int i = Integer.parseInt(value.replaceAll("[\\D]", ""));
//                if (doamdat_high) {
//                    seekSoiloff.setProgress(value);
//                    doamdat_high = false;
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//        seekSoiloff.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                textSoiloff.setText(progress + "%");
//                String soil = String.valueOf(progress);
//                int i = Integer.parseInt(soil);
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference highSoil = database.getReference("TGarden/Auto/Soil_off");
//                highSoil.setValue(i);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


//        ++++++++++++++++++++++++++++++++++++++++++++++++++++Setting Mode++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        Mode Auto Switch
        mSwAuto.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                if (value == 1) {
                    autoSwitch.setChecked(true);
                } else if (value == 0) {
                    autoSwitch.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        autoSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myAuto = database.getReference("TGarden/S1_Auto");
                    myAuto.setValue(1);
                    manualSwitch.setChecked(false);
                    alarmSwitch.setChecked(false);
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myAuto = database.getReference("TGarden/S1_Auto");
                    myAuto.setValue(0);
                }
            }
        });


//        Mode Manual Switch
        mSwManual.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                if (value == 1) {
                    manualSwitch.setChecked(true);
                } else if (value == 0) {
                    manualSwitch.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        manualSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myMan = database.getReference("TGarden/S1_Manual");
                    myMan.setValue(1);
                    autoSwitch.setChecked(false);
                    alarmSwitch.setChecked(false);
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myMan = database.getReference("TGarden/S1_Manual");
                    myMan.setValue(0);
                }
            }
        });


        //        Mode Alarm Switch
        mSwAlarm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(int.class);
                if (value == 1) {
                    alarmSwitch.setChecked(true);
                } else if (value == 0) {
                    alarmSwitch.setChecked(false);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myAlarm = database.getReference("TGarden/Alarm/Status");
                    myAlarm.setValue(1);
                    autoSwitch.setChecked(false);
                    manualSwitch.setChecked(false);
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myAlarm = database.getReference("TGarden/Alarm/Status");
                    myAlarm.setValue(0);
                }
            }
        });


        return view;
    }
}