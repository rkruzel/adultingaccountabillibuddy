package com.example.rob.capstoneandroidapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoginFragment extends Fragment
{

    // new instance of fragment
    public static LoginFragment newInstance()
    {
        return new LoginFragment();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        //TODO do stuff here


    }

    // Inflate fragment, return to fragment viewgroup container.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }












    //TODO code goes here
}