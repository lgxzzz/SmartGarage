package com.smartgarage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.smartgarage.bean.CarPort;
import com.smartgarage.data.DBManger;
import com.smartgarage.navi.overlay.DrivingRouteOverlay;
import com.smartgarage.navi.overlay.ToastUtil;
import com.smartgarage.view.TitleView;

public class RouteNaviActivity extends AppCompatActivity implements AMapNaviViewListener{
    private LatLonPoint mStartPoint = new LatLonPoint(39.942295, 116.335891);//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = new LatLonPoint(39.995576, 116.481288);//终点，116.481288,39.995576

    private TitleView mTitleView;
    private AMapNaviView mAMapNaviView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        initView(savedInstanceState);
        initData();
    }

    public void initView(Bundle savedInstanceState){
        mTitleView = findViewById(R.id.title_view);

        mTitleView.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获取 AMapNaviView 实例
        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.setAMapNaviViewListener(this);
        mAMapNaviView.onCreate(savedInstanceState);
    };

    public void initData(){
        CarPort carPort = (CarPort) getIntent().getExtras().getSerializable("carPort");
        boolean isFast = getIntent().getExtras().getBoolean("isFast");
        if (isFast){
            mTitleView.setTitle("导航路线");
        }else{
            mTitleView.setTitle("路线回放");
        }
        if (carPort!=null){
            LatLng startLng = DBManger.getInstance(this).getmCurPoint();
            mStartPoint = new LatLonPoint(startLng.latitude,startLng.longitude);
            mEndPoint = new LatLonPoint(carPort.getLat(),carPort.getLon());



        }
    };



    @Override
    protected void onResume() {
        super.onResume();
        mAMapNaviView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAMapNaviView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAMapNaviView.onDestroy();
    }

    @Override
    public void onNaviSetting() {

    }

    @Override
    public void onNaviCancel() {

    }

    @Override
    public boolean onNaviBackClick() {
        return false;
    }

    @Override
    public void onNaviMapMode(int i) {

    }

    @Override
    public void onNaviTurnClick() {

    }

    @Override
    public void onNextRoadClick() {

    }

    @Override
    public void onScanViewButtonClick() {

    }

    @Override
    public void onLockMap(boolean b) {

    }

    @Override
    public void onNaviViewLoaded() {

    }

    @Override
    public void onMapTypeChanged(int i) {

    }

    @Override
    public void onNaviViewShowMode(int i) {

    }
}
