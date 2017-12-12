package com.example.fr_philippe.gestionemploye;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

/**
 * Created by FR-PHILIPPE on 11/27/2017.
 */

public class HomeServicesChart extends Fragment{
    private BarChart mChart;
    private PieChart mChartP;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    public HomeServicesChart(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_services_chart, container, false);

        mChart=(BarChart)rootView.findViewById(R.id.chart1);
        mChart.getDescription().setEnabled(false);

        mChartP = (PieChart) rootView.findViewById(R.id.chart2);
        mChartP.setUsePercentValues(true);
        mChartP.getDescription().setEnabled(false);
        mChartP.setExtraOffsets(5, 10, 5, 5);

        mChartP.setDragDecelerationFrictionCoef(0.95f);

        //mChartP.setCenterTextTypeface(mTfLight);
        //mChartP.setCenterText(generateCenterSpannableText());

        mChartP.setDrawHoleEnabled(true);
        mChartP.setHoleColor(Color.WHITE);

        mChartP.setTransparentCircleColor(Color.WHITE);
        mChartP.setTransparentCircleAlpha(110);

        mChartP.setHoleRadius(58f);
        mChartP.setTransparentCircleRadius(61f);

        mChartP.setDrawCenterText(true);

        mChartP.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChartP.setRotationEnabled(true);
        mChartP.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
       // mChartP.setOnChartValueSelectedListener(this);

        setData(4, 100);

        mChartP.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        //mSeekBarX.setOnSeekBarChangeListener(this);
        //mSeekBarY.setOnSeekBarChangeListener(this);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChartP.setEntryLabelColor(Color.WHITE);
        //mChartP.setEntryLabelTypeface(mTfRegular);
        mChartP.setEntryLabelTextSize(12f);
        setData(4, 100);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        setData(10);
        mChart.setFitBars(true);
        return rootView;
    }

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            float val= (float)((Math.random() * mult) + mult / 5);
                    entries.add(new PieEntry(i,(int) val));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        //data.setValueTypeface(mTfLight);
        mChartP.setData(data);

        // undo all highlights
        mChartP.highlightValues(null);

        mChartP.invalidate();
    }


    private void setData(int count){
        ArrayList<BarEntry> yVals=new ArrayList<>();

        for(int i=0; i<count; i++){
            float value=(float)(Math.random()*100);
            yVals.add(new BarEntry(i,(int) value));
        }

        BarDataSet set=new BarDataSet(yVals,"Data Set");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);

        BarData data = new BarData(set);

        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(500);
    }

}
