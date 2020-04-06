package com.smartgarage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.smartgarage.ParkingSpaceActivity;
import com.smartgarage.R;
import com.smartgarage.UpdateActivity;
import com.smartgarage.adapter.CarPortAdapter;
import com.smartgarage.bean.CarPort;
import com.smartgarage.data.DBManger;
import com.smartgarage.navi.LocationMgr;
import com.smartgarage.navi.PoiSearchMgr;
import com.smartgarage.util.DataFactory;

import java.util.ArrayList;
import java.util.List;


public class GarageFragment extends Fragment{

    private PoiSearchMgr mPoiSearchMgr;
    private LocationMgr mLocationMgr;
    private LatLng mCurrentPosition; //最终选择的点

    private Double mLongitude;
    private Double mLatitude;
    private String cityCode;
    private String mKeyWord="停车场";

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
//        mCarPorts = DBManger.getInstance(getContext()).getDefaultCarPorts();


        mLocationMgr  = new LocationMgr(getContext());

        mPoiSearchMgr = new PoiSearchMgr(getContext());

        mPoiSearchMgr.setPoiListener(new PoiSearchMgr.PoiSearchListener() {

            @Override
            public void onSuccess(List<PoiItem> poiItems) {
                mCarPorts.clear();
                for (int i = 0;i<poiItems.size();i++){
                    PoiItem item = poiItems.get(i);
                    CarPort carPort = new CarPort();
                    //此部分是真实数据
                    carPort.setCarPortName(item.getTitle());
                    carPort.setAddress(item.getSnippet());
                    LatLonPoint latLonPoint = item.getLatLonPoint();
                    carPort.setLat(latLonPoint.getLatitude());
                    carPort.setLon(latLonPoint.getLongitude());
                    //此处是模拟数据
                    carPort = DataFactory.createCarPortData(carPort);
                    mCarPorts.add(carPort);
                }

                mAadapter = new CarPortAdapter(getContext(),mCarPorts);
                mCarPortsListView.setAdapter(mAadapter);
                DBManger.getInstance(getContext()).setmCarPorts(mCarPorts);
            }

            @Override
            public void onFail(String error) {

            }
        });

        getPosition();


    };

    public void doSearchQueryWithKeyWord(){
        mPoiSearchMgr.doSearchQuery(mKeyWord,mCurrentPosition.latitude,mCurrentPosition.longitude);
    }


    public void getPosition(){
        //获取定位信息并且查询当前的POI点周边
        mLocationMgr.getLonLat(getContext(), new LocationMgr.LonLatListener() {
            @Override
            public void getLonLat(AMapLocation aMapLocation) {
                mLongitude = aMapLocation.getLongitude();
                mLatitude = aMapLocation.getLatitude();
                cityCode = aMapLocation.getCityCode();
                mCurrentPosition = new LatLng(mLatitude,mLongitude);
                DBManger.getInstance(getContext()).setmCurPoint(mCurrentPosition);
                doSearchQueryWithKeyWord();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
