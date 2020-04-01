package com.smartgarage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.smartgarage.view.TitleView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TitleView mTitleView;
    private TextView mRegisterView;
    private EditText mNameEd;
    private EditText mPassWordEd;
    private String mName;
    private String mPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init(){
        mTitleView = findViewById(R.id.title_view);
        mTitleView.setBackBtnVisible(false);
        mTitleView.setTitle("用户登陆");
        mRegisterView = findViewById(R.id.login_to_register_btn);
        mRegisterView.setOnClickListener(this);

        mNameEd = findViewById(R.id.reg_name_ed);
        mPassWordEd = findViewById(R.id.reg_password_ed);

        mNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mName = editable.toString();
            }
        });

        mPassWordEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               mPassWord = editable.toString();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_to_register_btn:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.reg_login_btn:

                break;
        }
    }
}
