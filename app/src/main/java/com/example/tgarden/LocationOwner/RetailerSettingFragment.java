package com.example.tgarden.LocationOwner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tgarden.R;
import com.example.tgarden.User.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.units.qual.A;

public class RetailerSettingFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_retailer_setting, container, false );




        return view;
    }
}