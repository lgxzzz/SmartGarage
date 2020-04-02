package com.smartgarage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.smartgarage.bean.Car;
import com.smartgarage.bean.User;
import com.smartgarage.data.DBManger;
import com.smartgarage.view.TitleView;


public class AddCarActivity extends AppCompatActivity {

    private EditText mCarNumberEd;
    private EditText mCarModeEd;
    private Button mAddBtn;
    private TitleView mTitleView;
    private User mUser;
    private Car mCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);

        init();
    }

    public void init(){
        mUser = DBManger.getInstance(this).mUser;
        mCar = new Car();
        mCar.setUserId(mUser.getUserId());
        mTitleView = findViewById(R.id.title_view);
        mTitleView.setTitle("添加车辆");
        mTitleView.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCarActivity.this.finish();
            }
        });

        mCarNumberEd = findViewById(R.id.reg_car_number_ed);
        mCarModeEd = findViewById(R.id.reg_carmode_ed);
        mAddBtn = findViewById(R.id.addcar_sure_btn);


        mCarNumberEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mCar.setCarId(editable.toString());
            }
        });

        mCarModeEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mCar.setType(editable.toString());
            }
        });

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManger.getInstance(AddCarActivity.this).insertCar(mCar, new DBManger.IListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(AddCarActivity.this,"添加车辆成功！",Toast.LENGTH_LONG).show();
                        AddCarActivity.this.finish();

                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(AddCarActivity.this,"注册失败！",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
