package org.androidtown.simple_hw2_list;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Timetable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        LinearLayout M = (LinearLayout) findViewById(R.id.Mon);
        LinearLayout TU = (LinearLayout) findViewById(R.id.Tue);
        LinearLayout W = (LinearLayout) findViewById(R.id.Wed);
        LinearLayout TH = (LinearLayout) findViewById(R.id.Thur);
        LinearLayout F = (LinearLayout) findViewById(R.id.Fri);

        TextView Monday = (TextView) findViewById(R.id.monday);
        TextView Tuesday = (TextView) findViewById(R.id.thuesday);
        TextView Wednesday = (TextView) findViewById(R.id.wednesday);
        TextView Thursday = (TextView) findViewById(R.id.thursday);
        TextView Friday = (TextView) findViewById(R.id.friday);

        Calendar cal = Calendar.getInstance(); // declare the calendar in present time and date
        String week;// 요일 저장할 변수
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK); //day of week 1: sundays ~ 7:saturday

        // 현재 요일에 맞는 시간표가 강조 표시
        if (day_of_week == 1) {//Timetable don't have to show in the weekdend
            //week = "일요일";

        } else if (day_of_week == 2) {
            week = "월요일";
            if (Monday.getText().toString().compareTo(week) == 0) { //to change background color when the day of week is monday.
                M.setBackgroundColor(0xffff00ff);
            }
        } else if (day_of_week == 3) {
            week = "화요일";
            if (Tuesday.getText().toString().compareTo(week) == 0) {
                TU.setBackgroundColor(0xffff00ff);
            }
        } else if (day_of_week == 4) {
            week = "수요일";
            if (Wednesday.getText().toString().compareTo(week) == 0) {
                W.setBackgroundColor(0xffff00ff);
            }
        } else if (day_of_week == 5) {
            week = "목요일";
            if (Thursday.getText().toString().compareTo(week) == 0) {
                TH.setBackgroundColor(0xffff00ff);
            }
        } else if (day_of_week == 6) {
            week = "금요일";
            if (Friday.getText().toString().compareTo(week) == 0) {
                F.setBackgroundColor(0xffff00ff);
            }
        } else if (day_of_week == 7) {
            //week = "토요일";
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) { //to stay the data in rotating state.
// TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }
}
