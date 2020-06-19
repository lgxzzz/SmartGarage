package com.smartgarage.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.smartgarage.R;
import com.smartgarage.bean.Car;
import com.smartgarage.bean.CarPort;
import com.smartgarage.bean.Purchase;
import com.smartgarage.data.DBManger;
import com.smartgarage.util.DataFactory;
import com.smartgarage.view.FastToNaviDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FastFragment extends Fragment{

    private Spinner mCarsSp;
    private Spinner mCarPortSp;
    private Button mFastReserveBtn;
    private FastToNaviDialog mDialog;
    private CarPort mSelectCarPort;
    private Car mSelectCar;

    public List<Car> mCarInfos = new ArrayList<>();
    public List<CarPort> mCarPorts = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fast, container, false);
        initView(view);

        return view;
    }

    public static FastFragment getInstance() {
        return new FastFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initView(View view){
        mCarsSp = view.findViewById(R.id.spinner_car);
        mCarPortSp = view.findViewById(R.id.spinner_carport);
        mFastReserveBtn = view.findViewById(R.id.fast_reserve_btn);
        mDialog = new FastToNaviDialog(getContext(),R.layout.dialog_fast_to_navi,true,true);
    };

    public void initData(){
        mCarInfos = DBManger.getInstance(getContext()).getAllCars();
        mCarPorts = DBManger.getInstance(getContext()).getmCarPorts();
        ArrayList<String>carlist=new ArrayList<String>();
        for (int i= 0;i<mCarInfos.size();i++){
            Car car = mCarInfos.get(i);
            carlist.add(car.getType());
        }

        SpinnerAdapter adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,carlist);
        mCarsSp.setAdapter(adapter);

        ArrayList<String>carPortlist=new ArrayList<String>();
        for (int i= 0;i<mCarPorts.size();i++){
            CarPort car = mCarPorts.get(i);
            carPortlist.add(car.getCarPortName());
        }

        SpinnerAdapter adapter1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,carPortlist);
        mCarPortSp.setAdapter(adapter1);
        if (mCarPorts.size()>0){
            mSelectCarPort = mCarPorts.get(0);
        }

        mCarsSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectCar = mCarInfos.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (mCarInfos.size()>0){
            mSelectCar = mCarInfos.get(0);
        }
        mCarPortSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectCarPort = mCarPorts.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mFastReserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectCar == null){
                    Toast.makeText(getContext(),"请先选择车辆！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (mSelectCarPort == null){
                    Toast.makeText(getContext(),"请先选择停车场！",Toast.LENGTH_LONG).show();
                    return;
                }
                //快速预定是当前时间加半个小时
                long currentTime = System.currentTimeMillis() + 30 * 60 * 1000;
                Date date = new Date(currentTime);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowTime= df.format(date);
                //生成预定订单
                Purchase purchase = DataFactory.createPurchase(getContext(),mSelectCar,mSelectCarPort,nowTime);
                DBManger.getInstance(getContext()).setmOrderPurchase(purchase, new DBManger.IListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(String error) {

                    }
                });
                mDialog.setData(mSelectCarPort);
                mDialog.show();
            }
        });
    };
}
