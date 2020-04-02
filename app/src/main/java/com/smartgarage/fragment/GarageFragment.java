package com.smartgarage.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.smartgarage.R;
import com.smartgarage.adapter.CarPortAdapter;
import com.smartgarage.bean.CarPort;
import com.smartgarage.data.DBManger;

import java.util.ArrayList;
import java.util.List;


public class GarageFragment extends Fragment{


    List<CarPort> mCarPorts = new ArrayList<>();
    ListView mCarPortsListView;
    CarPortAdapter mAadapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_garage, container, false);
        initView(view);

        return view;
    }

    public static GarageFragment getInstance() {
        return new GarageFragment();
    }

    public void initView(View view){
        mCarPortsListView = view.findViewById(R.id.carport_listview);

    };

    public void initData(){
        mCarPorts = DBManger.getInstance(getContext()).getDefaultCarPorts();
        mAadapter = new CarPortAdapter(getContext(),mCarPorts);
        mCarPortsListView.setAdapter(mAadapter);
    };

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
