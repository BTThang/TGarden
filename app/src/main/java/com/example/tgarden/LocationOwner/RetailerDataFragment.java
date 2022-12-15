package com.example.tgarden.LocationOwner;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tgarden.R;
import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.ThingSpeakLineChart;

import java.util.Calendar;

import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class RetailerDataFragment extends Fragment {

    private ThingSpeakChannel channel1, channel2, channel3;
    private ThingSpeakLineChart chart1, chart2, chart3, chart4;
    private LineChartView chartView1, chartView2, chartView3, chartView4;

    public RetailerDataFragment(){

    }

    @SuppressLint("SetJavaScriptEnabled")

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retailer_data, container, false);
        channel1 = new ThingSpeakChannel(1980943);

        channel1.loadChannelFeed();

//        Creat a Calender object data 10 minutes ago
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -5);

        // Configure LineChartView
        chartView1 = view.findViewById(R.id.chart1);
        chartView2 = view.findViewById(R.id.chart2);
        chartView3 = view.findViewById(R.id.chart3);
        chartView4 = view.findViewById(R.id.chart4);

        chartView1.setZoomEnabled(true);
        chartView1.setValueSelectionEnabled(true);

        // Create a line chart from Field1 of ThinkSpeak Channel 9
        chart1 = new ThingSpeakLineChart(1980943, 1);
//        Get entries maximin
        chart1.setNumberOfEntries(10);
//        Set value axis lables on 10 unit interval
        chart1.setValueAxisLabelInterval(20);
//        Set date axis lable on 5 min interval
        chart1.setDateAxisLabelInterval(1);
//        Show the line as a cubic spline
        chart1.useSpline(true);
//        Set the line color
        chart1.setLineColor(Color.parseColor("#F793B3"));
        // Set the axis color
        chart1.setAxisColor(Color.parseColor("#D64E55"));
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        chart1.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        chart1.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                chartView1.setLineChartData(lineChartData);
//                Set scrolling bounds of the chart
                chartView1.setMaximumViewport(maxViewport);
//                Set the init chart bounds
                chartView1.setCurrentViewport(initialViewport);
                           /* LineChartData data = new LineChartData();
            float data1=data.getBaseValue();
            TextView tvName = (TextView)findViewById(R.id.textView);
            tvName.setText((int) data1);*/
            }
        });

        chart1.loadChartData();


        chartView2.setZoomEnabled(true);
        chartView2.setValueSelectionEnabled(true);

        // Create a line chart from Field1 of ThinkSpeak Channel 9
        chart2 = new ThingSpeakLineChart(1980943, 2);
//        Get entries maximin
        chart2.setNumberOfEntries(10);
//        Set value axis lables on 10 unit interval
        chart2.setValueAxisLabelInterval(20);
//        Set date axis lable on 5 min interval
        chart2.setDateAxisLabelInterval(1);
//        Show the line as a cubic spline
        chart2.useSpline(true);
//        Set the line color
        chart2.setLineColor(Color.parseColor("#558372"));
        // Set the axis color
        chart2.setAxisColor(Color.parseColor("#2A5A46"));
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        chart2.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        chart2.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                chartView2.setLineChartData(lineChartData);
//                Set scrolling bounds of the chart
                chartView2.setMaximumViewport(maxViewport);
//                Set the init chart bounds
                chartView2.setCurrentViewport(initialViewport);
                           /* LineChartData data = new LineChartData();
            float data1=data.getBaseValue();
            TextView tvName = (TextView)findViewById(R.id.textView);
            tvName.setText((int) data1);*/
            }
        });

        chart2.loadChartData();

//Chart3

        chartView3.setZoomEnabled(true);
        chartView3.setValueSelectionEnabled(true);

        // Create a line chart from Field1 of ThinkSpeak Channel 9
        chart3 = new ThingSpeakLineChart(1980943, 3);
//        Get entries maximin
        chart3.setNumberOfEntries(10);
//        Set value axis lables on 10 unit interval
        chart3.setValueAxisLabelInterval(20);
//        Set date axis lable on 5 min interval
        chart3.setDateAxisLabelInterval(1);
//        Show the line as a cubic spline
        chart3.useSpline(true);
//        Set the line color
        chart3.setLineColor(Color.parseColor("#E69058"));//E69058   558372
        // Set the axis color
        chart3.setAxisColor(Color.parseColor("#C55849"));//C55849  2A5A46

        // Set the starting date (5 minutes ago) for the default viewport of the chart
        chart3.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        chart3.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                chartView3.setLineChartData(lineChartData);
//                Set scrolling bounds of the chart
                chartView3.setMaximumViewport(maxViewport);
//                Set the init chart bounds
                chartView3.setCurrentViewport(initialViewport);
                           /* LineChartData data = new LineChartData();
            float data1=data.getBaseValue();
            TextView tvName = (TextView)findViewById(R.id.textView);
            tvName.setText((int) data1);*/
            }
        });

        chart3.loadChartData();

        //Chart3

        chartView4.setZoomEnabled(true);
        chartView4.setValueSelectionEnabled(true);

        // Create a line chart from Field1 of ThinkSpeak Channel 9
        chart4 = new ThingSpeakLineChart(1980943, 4);
//        Get entries maximin
        chart4.setNumberOfEntries(10);
//        Set value axis lables on 10 unit interval
        chart4.setValueAxisLabelInterval(20);
//        Set date axis lable on 5 min interval
        chart4.setDateAxisLabelInterval(1);
//        Show the line as a cubic spline
        chart4.useSpline(true);
//        Set the line color
        chart4.setLineColor(Color.parseColor("#7018F8")); // mau hien thi
        // Set the axis color4238CB
        chart4.setAxisColor(Color.parseColor("#4238CB")); //toa do
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        chart4.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        chart4.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                chartView4.setLineChartData(lineChartData);
//                Set scrolling bounds of the chart
                chartView4.setMaximumViewport(maxViewport);
//                Set the init chart bounds
                chartView4.setCurrentViewport(initialViewport);
                           /* LineChartData data = new LineChartData();
            float data1=data.getBaseValue();
            TextView tvName = (TextView)findViewById(R.id.textView);
            tvName.setText((int) data1);*/
            }
        });

        chart4.loadChartData();


        return view;
    }
}