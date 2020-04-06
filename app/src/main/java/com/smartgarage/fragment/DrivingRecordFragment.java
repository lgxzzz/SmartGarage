package com.smartgarage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smartgarage.DrivingRecordActivity;
import com.smartgarage.FastNaviActivity;
import com.smartgarage.PathActivity;
import com.smartgarage.R;


public class DrivingRecordFragment extends Fragment{

    private Button mStartBtn;
    private Button mEndBtn;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_drivingrecord, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public static DrivingRecordFragment getInstance() {
        return new DrivingRecordFragment();
    }

    public void initView(View view){
        mStartBtn = view.findViewById(R.id.drving_record_start_btn);
        mEndBtn = view.findViewById(R.id.drving_record_end_btn);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(),DrivingRecordActivity.class);
                Bundle b = new Bundle();
                b.putBoolean("isRecord",true);
                intent.putExtras(b);
                getContext().startActivity(intent);
            }
        });

        mEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(),PathActivity.class);
                getContext().startActivity(intent);
            }
        });
    };

    public void initData(){

    };
}
