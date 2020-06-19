package com.smartgarage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import com.smartgarage.bean.Car;
import com.smartgarage.bean.CarPort;
import com.smartgarage.bean.Purchase;
import com.smartgarage.bean.User;
import com.smartgarage.data.DBManger;
import com.smartgarage.util.DataFactory;
import com.smartgarage.view.FastToNaviDialog;
import com.smartgarage.view.SelectDateDialog;
import com.smartgarage.view.TitleView;

import java.util.ArrayList;
import java.util.List;


public class SelectCarActivity extends AppCompatActivity {

    private Spinner mCarsSp;
    private Button mDateSp;
    private Button mTimeSp;
    private Button mReserveBtn;
    private CarPort mSelectCarPort;
    private Car mSelectCar;
    private SelectDateDialog mSelectDateDialog;
    public List<Car> mCarInfos = new ArrayList<>();

    boolean isSelectDate = false;
    boolean isSelectTime = false;

    String mSelectTime = "";
    String mSelectDate = "";
    String mSelectFianlTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_car);

        initView();
        initData();
    }

    public void initView(){
        mCarsSp = findViewById(R.id.spinner_car);
        mReserveBtn = findViewById(R.id.reserve_btn);
        mDateSp = findViewById(R.id.spinner_date);
        mTimeSp = findViewById(R.id.spinner_time);

        mSelectDateDialog = new SelectDateDialog(this, R.layout.dialog_select_date, true, true);
    };

    public void initData(){

        mSelectCarPort = (CarPort) getIntent().getExtras().getSerializable("carPort");

        mCarInfos = DBManger.getInstance(this).getAllCars();
        ArrayList<String>carlist=new ArrayList<String>();
        for (int i= 0;i<mCarInfos.size();i++){
            Car car = mCarInfos.get(i);
            carlist.add(car.getType());
        }

        SpinnerAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,carlist);
        mCarsSp.setAdapter(adapter);

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

        mDateSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectDateDialog.setSelectDate(true);
                mSelectDateDialog.show();
            }
        });

        mTimeSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectDateDialog.setSelectDate(false);
                mSelectDateDialog.show();
            }
        });

        mSelectDateDialog.setListener(new SelectDateDialog.IOnSelectListener() {
            @Override
            public void onSelectDate(String date) {
                isSelectDate = true;
                mSelectDate = date;
                mDateSp.setText(date);
            }

            @Override
            public void onSelectTime(String time) {
                isSelectTime = true;
                mSelectTime = time;
                mTimeSp.setText(time);
            }
        });


        mReserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectCar == null){
                    Toast.makeText(SelectCarActivity.this,"请先选择车辆！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!isSelectDate){
                    Toast.makeText(SelectCarActivity.this,"请先选择日期！",Toast.LENGTH_LONG).show();
                    return;
                }
                if (!isSelectTime){
                    Toast.makeText(SelectCarActivity.this,"请先选择时间！",Toast.LENGTH_LONG).show();
                    return;
                }
                mSelectFianlTime = mSelectDate +" "+mSelectTime;
                //生成预定订单
                Purchase purchase = DataFactory.createPurchase(SelectCarActivity.this,mSelectCar,mSelectCarPort,mSelectFianlTime);
                DBManger.getInstance(SelectCarActivity.this).setmOrderPurchase(purchase, new DBManger.IListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(SelectCarActivity.this,"预定成功，点击个人中心查看",Toast.LENGTH_LONG).show();
                        Intent intentmain=new Intent(SelectCarActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentmain);
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
            }
        });
    };
}
