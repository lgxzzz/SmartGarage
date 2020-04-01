package com.smartgarage;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.smartgarage.fragment.AboutFragment;
import com.smartgarage.fragment.FastFragment;
import com.smartgarage.fragment.GarageFragment;
import com.smartgarage.fragment.NaviFragment;
import com.smartgarage.util.FragmentUtils;
import com.smartgarage.view.TitleView;


public class MainActivity extends BaseActivtiy {

    private BottomNavigationView mBottomMenu;
    private TitleView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    public void init(){
        mBottomMenu = findViewById(R.id.bottom_menu);
        mBottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showFragment(item.getItemId());
                return true;
            }
        });

        mTitleView = findViewById(R.id.title_view);
        mTitleView.setBackBtnVisible(false);

        mBottomMenu.setSelectedItemId(R.id.bottom_menu_about);

    }


    /**
     * 根据id显示相应的页面
     * @param menu_id
     */
    private void showFragment(int menu_id) {
        switch (menu_id){
            case R.id.bottom_menu_garage:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, GarageFragment.getInstance(),R.id.main_frame);
                mTitleView.setTitle("车位查询");
                break;
            case R.id.bottom_menu_fast:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, FastFragment.getInstance(),R.id.main_frame);
                mTitleView.setTitle("快速预定");
                break;
            case R.id.bottom_menu_navi:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, NaviFragment.getInstance(),R.id.main_frame);
                mTitleView.setTitle("行车记录");
                break;
            case R.id.bottom_menu_about:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, AboutFragment.getInstance(),R.id.main_frame);
                mTitleView.setTitle("个人中心");
                break;

        }
    }
}
