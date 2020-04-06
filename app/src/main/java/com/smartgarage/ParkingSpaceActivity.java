package com.smartgarage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.smartgarage.bean.CarPort;
import com.smartgarage.view.TitleView;

public class ParkingSpaceActivity extends Activity{
    private TitleView mTitleView;
    private TextView mGarageName;

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

        mGarageName = findViewById(R.id.garage_name);

        final CarPort carPort = (CarPort) getIntent().getExtras().getSerializable("carPort");
        if (carPort!=null){
            mGarageName.setText(carPort.getCarPortName());
        }
    }
}
