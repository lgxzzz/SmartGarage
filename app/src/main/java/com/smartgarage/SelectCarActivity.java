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
import android.widget.Toast;

import com.smartgarage.bean.Car;
import com.smartgarage.bean.CarPort;
import com.smartgarage.bean.Purchase;
import com.smartgarage.bean.User;
import com.smartgarage.data.DBManger;
import com.smartgarage.util.DataFactory;
import com.smartgarage.view.FastToNaviDialog;
import com.smartgarage.view.TitleView;

import java.util.ArrayList;
import java.util.List;


public class SelectCarActivity extends AppCompatActivity {

    private Spinner mCarsSp;
    private Button mReserveBtn;
    private CarPort mSelectCarPort;
    private Car mSelectCar;

    public List<Car> mCarInfos = new ArrayList<>();
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


        mReserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectCar == null){
                    Toast.makeText(SelectCarActivity.this,"请先选择车辆！",Toast.LENGTH_LONG).show();
                    return;
                }

                //生成预定订单
                Purchase purchase = DataFactory.createPurchase(SelectCarActivity.this,mSelectCar,mSelectCarPort);
                DBManger.getInstance(SelectCarActivity.this).setmOrderPurchase(purchase);
                Toast.makeText(SelectCarActivity.this,"预定成功，点击个人中心查看",Toast.LENGTH_LONG).show();
                Intent intentmain=new Intent(SelectCarActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentmain);
            }
        });
    };
}
