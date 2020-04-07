package com.smartgarage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.smartgarage.adapter.ParkSpaceAdapter;
import com.smartgarage.bean.CarPort;
import com.smartgarage.bean.ParkingSpaceInfo;
import com.smartgarage.view.TitleView;

import java.util.ArrayList;
import java.util.List;

public class ParkingSpaceActivity extends Activity{
    private TitleView mTitleView;
    private TextView mGarageName;
    private ListView mParkFillListView;
    private ListView mParkEmptyListView;
    private ParkSpaceAdapter mFillAdapter;
    private ParkSpaceAdapter mEmptyAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkingplace);
        init();
    }

    public void init(){
        mTitleView = findViewById(R.id.title_view);
        mTitleView.setTitle("车位信息");
        mTitleView.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mParkFillListView = findViewById(R.id.park_fill_listview);
        mParkEmptyListView = findViewById(R.id.park_empty_listview);

        mGarageName = findViewById(R.id.garage_name);

        final CarPort carPort = (CarPort) getIntent().getExtras().getSerializable("carPort");
        if (carPort!=null){
            mGarageName.setText(carPort.getCarPortName());
            List<ParkingSpaceInfo> parkFill =new ArrayList<>();
            List<ParkingSpaceInfo> parkEmpty =new ArrayList<>();
            for (int i=0;i<carPort.getContent();i++){
                ParkingSpaceInfo parkingSpaceInfo = carPort.getmParkingSpaceInfos().get(i);
                if (parkingSpaceInfo.getState().equals("已停")){
                    parkFill.add(parkingSpaceInfo);
                }else if(parkingSpaceInfo.getState().equals("空")){
                    parkEmpty.add(parkingSpaceInfo);
                }
            }

            mEmptyAdapter = new ParkSpaceAdapter(ParkingSpaceActivity.this,parkEmpty);
            mParkEmptyListView.setAdapter(mEmptyAdapter);
            mEmptyAdapter.setListener(new ParkSpaceAdapter.IOrderListener() {
                @Override
                public void onOrder(ParkingSpaceInfo spaceInfo) {
                    mFillAdapter.refreshData(spaceInfo);
                }
            });

            mFillAdapter = new ParkSpaceAdapter(ParkingSpaceActivity.this,parkFill);
            mParkFillListView.setAdapter(mFillAdapter);
        }
    }
}
