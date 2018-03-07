package com.example.rob.timecard;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Rob on 1/4/2018.
 */

public class Main extends AppCompatActivity
{

    //GUI things
    private Button newJob = null;
    private Button endJob = null;
    private Button endDay = null;


    private String userName = "RK";

    private ListView listView;
    private ArrayAdapter<String> adapter;

    private ArrayList<String> currentJob  = null;
    private List<ArrayList<String>> jobData = new ArrayList<ArrayList<String>>();


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        newJob = (Button) findViewById(R.id.startJob);
        endJob = (Button) findViewById(R.id.endJob);
        endDay = (Button) findViewById(R.id.endDay);

        newJob.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String checkrocks = null;
                
                //dostuff


                String temp1 = "test";
                String temp2 = "test2";


                '\n\n'
            }
        });

        endJob.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //dostuff
            }
        });

        endDay.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //dostuff
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }


    public String getTime()
    {
        Long time = System.currentTimeMillis()/1000;
        return time.toString();
    }

    public void newJob(String name, String type)
    {
        if (currentJob != null)
        {
            endJob("N/A", "N/A");
        }

        currentJob.clear();
        currentJob.add(getTime());
        currentJob.add(name);
        currentJob.add(type);
    }


    public void endJob(String qty, String notes)
    {

        if (currentJob != null)
        {
            currentJob.add(getTime());
            currentJob.add(qty);
            currentJob.add(notes);
        }

        {

            jobData.put()
            currentJob = null;
            adapter.notifyDataSetChanged();
        }
    }


    public String convertToText()
    {
        //TODO this probably doesnt work

        StringBuilder temp = new StringBuilder();
        for (Vector<String> vector : jobData)
        {
            temp.append(jobData.toString());
            temp.append(System.getProperty("line.separator"));
        }

        String string = temp.toString();
        return string;
    }

    public void endDay()
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"robkruzelwins@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Work Timecard");
        intent.putExtra(Intent.EXTRA_TEXT , "");

        try
        {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        }
        catch (ActivityNotFoundException e)
        {
            Toast.makeText(Main.this, "Unable to find email client.", Toast.LENGTH_SHORT).show();
        }

    }

}
