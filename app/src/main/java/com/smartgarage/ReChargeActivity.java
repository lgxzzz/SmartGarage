package com.smartgarage;

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

import com.smartgarage.bean.AddMoney;
import com.smartgarage.bean.Car;
import com.smartgarage.bean.User;
import com.smartgarage.data.DBManger;
import com.smartgarage.util.DataFactory;
import com.smartgarage.view.TitleView;

import java.util.ArrayList;


public class ReChargeActivity extends AppCompatActivity {

    private Spinner mReChargeTypeSp;
    private EditText mReChargeNumberEd;
    private Button mAddBtn;
    private TitleView mTitleView;
    private User mUser;
    private  AddMoney addMoney;
    private String PayWay;
    private String AddNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        init();
    }

    public void init(){
        mUser = DBManger.getInstance(this).mUser;

        mTitleView = findViewById(R.id.title_view);
        mTitleView.setTitle("添加车辆");
        mTitleView.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReChargeActivity.this.finish();
            }
        });

        mReChargeTypeSp = findViewById(R.id.recharge_type_sp);
        mReChargeNumberEd = findViewById(R.id.recharge_number_ed);
        mAddBtn = findViewById(R.id.addcar_sure_btn);

        final ArrayList<String> typeList=new ArrayList<String>();
        typeList.add("支付宝");
        typeList.add("微信");
        typeList.add("银行卡");
        typeList.add("现金");

        SpinnerAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,typeList);
        mReChargeTypeSp.setAdapter(adapter);
        mReChargeTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PayWay = typeList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        PayWay = typeList.get(0);


        mReChargeNumberEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                AddNumber = editable.toString();
            }
        });

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoney = DataFactory.createAddMoney(mUser,PayWay,AddNumber);

                DBManger.getInstance(ReChargeActivity.this).insertAddMoney(addMoney, new DBManger.IListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(ReChargeActivity.this,"充值成功！",Toast.LENGTH_LONG).show();
                        ReChargeActivity.this.finish();
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(ReChargeActivity.this,"充值失败！",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
