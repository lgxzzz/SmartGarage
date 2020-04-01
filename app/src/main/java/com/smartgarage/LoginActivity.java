package com.smartgarage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.smartgarage.view.TitleView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TitleView mTitleView;
    private TextView mRegisterView;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_to_register_btn:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }
}
