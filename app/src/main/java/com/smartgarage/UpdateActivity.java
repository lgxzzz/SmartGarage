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

import com.smartgarage.bean.User;
import com.smartgarage.data.DBManger;
import com.smartgarage.view.TitleView;


public class UpdateActivity extends AppCompatActivity {

    private EditText mNameEd;
    private RadioGroup mSexRg;
    private EditText mIDEd;
    private EditText mConnectEd;
    private Button mUpdateBtn;
    private Button mPasswordBtn;
    private TitleView mTitleView;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        init();
    }

    public void init(){
        mUser = DBManger.getInstance(this).mUser;

        mTitleView = findViewById(R.id.title_view);
        mTitleView.setTitle("修改信息");
        mTitleView.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateActivity.this.finish();
            }
        });

        mNameEd = findViewById(R.id.reg_name_ed);
        mIDEd = findViewById(R.id.reg_ID_ed);
        mConnectEd = findViewById(R.id.reg_connect_ed);
        mSexRg = findViewById(R.id.reg_sex_rg);
        mUpdateBtn = findViewById(R.id.update_sure_btn);
        mPasswordBtn = findViewById(R.id.password_update_btn);

        mNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mUser.setUserName(editable.toString());
            }
        });

        mIDEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mUser.setIdCard(editable.toString());
            }
        });

        mSexRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String sex= "男";
                switch(i){
                    case R.id.reg_sex_women:
                        sex = "女";
                    break;
                    default:

                    break;
                }
                mUser.setSex(sex);
            }
        });

        mConnectEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mUser.setTelephone(editable.toString());
            }
        });

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUser.getUserName()==null){
                    Toast.makeText(UpdateActivity.this,"用户名不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }

                DBManger.getInstance(UpdateActivity.this).updateUser(mUser, new DBManger.IListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(UpdateActivity.this,"更新成功！",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(UpdateActivity.this,"注册失败！",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
