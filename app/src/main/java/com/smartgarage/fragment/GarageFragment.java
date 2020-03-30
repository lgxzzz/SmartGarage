package com.smartgarage.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartgarage.R;


public class GarageFragment extends Fragment{




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_garage, container, false);
        initView();
        initData();
        return view;
    }

    public static GarageFragment getInstance() {
        return new GarageFragment();
    }

    public void initView(){};

    public void initData(){

    };
}
