package com.example.tgarden.LocationOwner;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tgarden.R;
import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.ThingSpeakLineChart;

import lecho.lib.hellocharts.view.LineChartView;

public class RetailerDataFragment extends Fragment {

    private ThingSpeakChannel channel1, channel2, channel3;
    private ThingSpeakLineChart chart1, chart2, chart3;
    private LineChartView chartView1, chartView2, chartView3;

    public RetailerDataFragment(){

    }

    @SuppressLint("SetJavaScriptEnabled")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retailer_data, container, false);
        channel1 = new ThingSpeakChannel(884467);


        return view;
    }
}