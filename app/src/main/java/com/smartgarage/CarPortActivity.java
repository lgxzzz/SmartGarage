package com.smartgarage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.smartgarage.bean.CarPort;
import com.smartgarage.view.TitleView;

public class CarPortActivity extends Activity{

    private TitleView mTitleView;
    private TextView mGarageName;
    private TextView mGarageAddress;
    private TextView mParkingPlaceBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);

        init();
    }

    public void init(){
        mTitleView = findViewById(R.id.title_view);
        mTitleView.setTitle("车库信息");

        mGarageName = findViewById(R.id.garage_name);
        mGarageAddress = findViewById(R.id.garage_address);
        mParkingPlaceBtn = findViewById(R.id.parkingplace_detail_btn);

        final CarPort carPort = (CarPort) getIntent().getExtras().getSerializable("carPort");
        if (carPort!=null){
            mGarageAddress.setText(carPort.getAddress());
            mGarageName.setText(carPort.getCarPortName());
        }

        mParkingPlaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(CarPortActivity.this,ParkingSpaceActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("carPort",carPort);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
