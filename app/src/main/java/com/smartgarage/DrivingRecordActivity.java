package com.smartgarage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.smartgarage.bean.CarPort;
import com.smartgarage.bean.PathInfo;
import com.smartgarage.data.DBManger;
import com.smartgarage.navi.overlay.DrivingRouteOverlay;
import com.smartgarage.navi.overlay.ToastUtil;
import com.smartgarage.view.SaveRecordDialog;
import com.smartgarage.view.TitleView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DrivingRecordActivity extends AppCompatActivity implements RouteSearch.OnRouteSearchListener{
    private MapView mMapView = null;
    private RouteSearch mRouteSearch;
    private LatLonPoint mStartPoint = new LatLonPoint(39.942295, 116.335891);//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = new LatLonPoint(39.995576, 116.481288);//终点，116.481288,39.995576
    private AMap mAMap;
    private DriveRouteResult mDriveRouteResult;
    private Button mEndBtn;
    private SaveRecordDialog mDialog;
    private TitleView mTitleView;
    private CarPort mCarPort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivingrecord);
        initView(savedInstanceState);
        initData();
    }

    public void initView(Bundle savedInstanceState){
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        mAMap = mMapView.getMap();
        mRouteSearch = new RouteSearch(this);
        mRouteSearch.setRouteSearchListener(this);
        mDialog = new SaveRecordDialog(this,R.layout.dialog_save_record,true,true);
        mEndBtn = findViewById(R.id.record_end_btn);
        mEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
            }
        });
        mTitleView = findViewById(R.id.title_view);
        mTitleView.setTitle("行车记录");
        mTitleView.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDialog.setlistener(new SaveRecordDialog.IOnSureListener() {
            @Override
            public void onSure(String name) {
                PathInfo pathInfo = new PathInfo();
                pathInfo.setPathName(name);
                pathInfo.setPort(mCarPort);
                pathInfo.setTime(getDate());
                DBManger.getInstance(DrivingRecordActivity.this).getmPaths().add(pathInfo);
                finish();
            }
        });
    };

    public String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public void initData(){
        List<CarPort> mCarPorts = DBManger.getInstance(this).getmCarPorts();
        if (mCarPorts.size()!=0){
            mCarPort = mCarPorts.get(0);
            LatLng startLng = DBManger.getInstance(this).getmCurPoint();
            mStartPoint = new LatLonPoint(startLng.latitude,startLng.longitude);
            mEndPoint = new LatLonPoint(mCarPorts.get(0).getLat(),mCarPorts.get(0).getLon());
            searchRouteResult();
        }
    };

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
        mAMap.clear();
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mDriveRouteResult = result;
                    final DrivePath drivePath = mDriveRouteResult.getPaths()
                            .get(0);
                    if(drivePath == null) {
                        return;
                    }
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(
                            DrivingRecordActivity.this, mAMap, drivePath,
                            mDriveRouteResult.getStartPos(),
                            mDriveRouteResult.getTargetPos(), null);
                    drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
                    drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();
                    int dis = (int) drivePath.getDistance();
                    int dur = (int) drivePath.getDuration();

                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.show(DrivingRecordActivity.this, R.string.no_result);
                }

            } else {
                ToastUtil.show(DrivingRecordActivity.this, R.string.no_result);
            }
        } else {
            ToastUtil.showerror(DrivingRecordActivity.this, errorCode);
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult() {
        int mode=-1;

        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStartPoint
                , mEndPoint);
        mode = RouteSearch.DrivingDefault;
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, mode, null,
                null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
        mRouteSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
        mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds(
                new LatLng(mStartPoint.getLatitude(),mStartPoint.getLongitude()),new LatLng(mEndPoint.getLatitude(), mEndPoint.getLongitude())),20));
    }
}
