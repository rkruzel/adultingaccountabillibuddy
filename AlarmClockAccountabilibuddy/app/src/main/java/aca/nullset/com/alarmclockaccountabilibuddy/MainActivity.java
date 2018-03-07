package aca.nullset.com.alarmclockaccountabilibuddy;

// Copyright Robert Kruzel 3/2/2018

import android.app.AlarmManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
{
    private AlarmManager alarmManager;
    private Button setAlarm;
    private Button stopAlarm;
    private Button settings;
    private Spinner alarmHour;
    private Spinner alarmMinute;
    private Spinner alarmAMPM;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.stop_alarm:

            case R.id.settings:

            case R.id.set_alarm:

            case R.id.alarm_hour:

            case R.id.alarm_minute:

            case R.id.alarm_ampm:
        }
    }

    private void alarmSet()
    {

    }


}
