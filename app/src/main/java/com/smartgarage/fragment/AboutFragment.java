package com.smartgarage.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.smartgarage.AddCarActivity;
import com.smartgarage.FastNaviActivity;
import com.smartgarage.LoginActivity;
import com.smartgarage.R;
import com.smartgarage.ReChargeActivity;
import com.smartgarage.RouteNaviActivity;
import com.smartgarage.SplashActivity;
import com.smartgarage.UpdateActivity;
import com.smartgarage.adapter.AddMoneyAdapter;
import com.smartgarage.adapter.CarAdapter;
import com.smartgarage.adapter.PurchaseAdapter;
import com.smartgarage.bean.AddMoney;
import com.smartgarage.bean.Car;
import com.smartgarage.bean.Purchase;
import com.smartgarage.bean.User;
import com.smartgarage.data.DBManger;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment implements View.OnClickListener{

    ViewGroup mAboutPerson;
    ViewGroup mAboutCar;
    ViewGroup mAboutPurchase;
    ViewGroup mAboutRecharge;

    Button mPersonBtn;
    Button mCarBtn;
    Button mPurchaseBtn;
    Button mRechargeBtn;

    //个人信息
    TextView mUserID;
    TextView mUserName;
    TextView mUserSex;
    TextView mUserIDCard;
    TextView mUserTel;
    TextView mUserRFI;
    Button mUpdateBtn;

    ViewGroup mOrderLayout;
    TextView mOrderGarageName;
    TextView mOrderParkingID;
    TextView mOrderTime;
    TextView mOrderCar;
    TextView mOrderNavi;

    //车辆信息
    Button mAddCarBtn;
    ListView mCarListView;
    CarAdapter mCarAdpater;
    List<Car> mCarInfos = new ArrayList<>();

    //消费记录
    List<Purchase> mPurchases = new ArrayList<>();
    ListView mPurchaseListview;
    PurchaseAdapter mPurchaseAdapter;

    //充值记录
    ListView mAddMoneyListView;
    List<AddMoney> mAddMoneys = new ArrayList<>();
    AddMoneyAdapter mAddMoneyAdapter;
    TextView mRFIid;
    TextView mRemain;
    Button mAddRechargeBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_about, container, false);
        initView(view);
        return view;
    }

    public static AboutFragment getInstance() {
        return new AboutFragment();
    }

    public void initView(View view ){
        mPersonBtn = view.findViewById(R.id.about_person_btn);
        mCarBtn = view.findViewById(R.id.about_car_btn);
        mPurchaseBtn = view.findViewById(R.id.about_purchase_btn);
        mRechargeBtn = view.findViewById(R.id.about_recharge_btn);

        mPersonBtn.setOnClickListener(this);
        mCarBtn.setOnClickListener(this);
        mPurchaseBtn.setOnClickListener(this);
        mRechargeBtn.setOnClickListener(this);

        mAboutPerson = view.findViewById(R.id.about_person_info_layout);
        mAboutCar = view.findViewById(R.id.about_car_info_layout);
        mAboutPurchase = view.findViewById(R.id.about_purchase_info_layout);
        mAboutRecharge = view.findViewById(R.id.about_recharge_info_layout);

        mUserID = view.findViewById(R.id.user_id);
        mUserName = view.findViewById(R.id.user_name);
        mUserSex = view.findViewById(R.id.user_sex);
        mUserIDCard = view.findViewById(R.id.user_idcard);
        mUserTel = view.findViewById(R.id.user_tel);
        mUserRFI = view.findViewById(R.id.user_RFI);
        mUpdateBtn = view.findViewById(R.id.user_update_btn);
        mUpdateBtn.setOnClickListener(this);

        mAddCarBtn = view.findViewById(R.id.add_car_btn);
        mAddCarBtn.setOnClickListener(this);
        mCarListView = view.findViewById(R.id.add_car_listview);

        mPurchaseListview = view.findViewById(R.id.purchase_list);
        mAddMoneyListView = view.findViewById(R.id.recharge_listview);

        mRFIid = view.findViewById(R.id.RFI_id);
        mRemain = view.findViewById(R.id.RFI_remain);
        mAddRechargeBtn = view.findViewById(R.id.add_recharge_btn);
        mAddRechargeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ReChargeActivity.class);
                getContext().startActivity(intent);
            }
        });


        mOrderLayout = view.findViewById(R.id.about_person_order_layout);
        mOrderGarageName = view.findViewById(R.id.order_garage_name);
        mOrderParkingID = view.findViewById(R.id.order_parking_id);
        mOrderTime = view.findViewById(R.id.order_time);
        mOrderCar = view.findViewById(R.id.order_car);
        mOrderNavi = view.findViewById(R.id.order_navi);


    };

    public void initData(){
        User user = DBManger.getInstance(getContext()).mUser;
        mUserID.setText(user.getUserId());
        mUserName.setText(user.getUserName());
        mUserSex.setText(user.getSex());
        mUserIDCard.setText(user.getIdCard());
        mUserTel.setText(user.getTelephone());
        mUserRFI.setText(user.getRFIID());

        mCarInfos = DBManger.getInstance(getContext()).getAllCars();
        mCarAdpater = new CarAdapter(getContext(),mCarInfos);
        mCarListView.setAdapter(mCarAdpater);

        mPurchases = DBManger.getInstance(getContext()).getPurchases();
        mPurchaseAdapter = new PurchaseAdapter(getContext(),mPurchases);
        mPurchaseListview.setAdapter(mPurchaseAdapter);

        mAddMoneys = DBManger.getInstance(getContext()).getAddMoneys();
        mAddMoneyAdapter = new AddMoneyAdapter(getContext(),mAddMoneys);
        mAddMoneyListView.setAdapter(mAddMoneyAdapter);

        //计算余额
        String Remain = CalculateRemain();
        mRemain.setText(Remain);

        mRFIid.setText(user.getRFIID());

        final Purchase mOrder =DBManger.getInstance(getContext()).getmOrderPurchase();
        if (mOrder!=null){
            mOrderLayout.setVisibility(View.VISIBLE);
            mOrderGarageName.setText(mOrder.getCarPort().getCarPortName());
            mOrderParkingID.setText(mOrder.getCarPort().getEmptyParkingSpaceInfo().getPlaceId());
            mOrderTime.setText(mOrder.getBillDate());
            mOrderCar.setText(mOrder.getCar().getType());

            mOrderNavi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), RouteNaviActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("carPort",mOrder.getCarPort());
                    b.putBoolean("isFastNavi",true);
                    intent.putExtras(b);
                    getContext().startActivity(intent);
                }
            });
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_person_btn:
                mPersonBtn.setTextColor(Color.BLUE);
                mCarBtn.setTextColor(Color.BLACK);
                mPurchaseBtn.setTextColor(Color.BLACK);
                mRechargeBtn.setTextColor(Color.BLACK);

                mAboutPerson.setVisibility(View.VISIBLE);
                mAboutCar.setVisibility(View.GONE);
                mAboutPurchase.setVisibility(View.GONE);
                mAboutRecharge.setVisibility(View.GONE);
                break;
            case R.id.about_car_btn:
                mPersonBtn.setTextColor(Color.BLACK);
                mCarBtn.setTextColor(Color.BLUE);
                mPurchaseBtn.setTextColor(Color.BLACK);
                mRechargeBtn.setTextColor(Color.BLACK);

                mAboutPerson.setVisibility(View.GONE);
                mAboutCar.setVisibility(View.VISIBLE);
                mAboutPurchase.setVisibility(View.GONE);
                mAboutRecharge.setVisibility(View.GONE);
                break;
            case R.id.about_purchase_btn:
                mPersonBtn.setTextColor(Color.BLACK);
                mCarBtn.setTextColor(Color.BLACK);
                mPurchaseBtn.setTextColor(Color.BLUE);
                mRechargeBtn.setTextColor(Color.BLACK);

                mAboutPerson.setVisibility(View.GONE);
                mAboutCar.setVisibility(View.GONE);
                mAboutPurchase.setVisibility(View.VISIBLE);
                mAboutRecharge.setVisibility(View.GONE);
                break;
            case R.id.about_recharge_btn:

                mPersonBtn.setTextColor(Color.BLACK);
                mCarBtn.setTextColor(Color.BLACK);
                mPurchaseBtn.setTextColor(Color.BLACK);
                mRechargeBtn.setTextColor(Color.BLUE);
                mAboutPerson.setVisibility(View.GONE);
                mAboutCar.setVisibility(View.GONE);
                mAboutPurchase.setVisibility(View.GONE);
                mAboutRecharge.setVisibility(View.VISIBLE);
                break;
            case R.id.user_update_btn:
                startActivity(new Intent(getContext(), UpdateActivity.class));
                break;
            case R.id.add_car_btn:
                startActivity(new Intent(getContext(), AddCarActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    //计算余额
    public String CalculateRemain(){
        String remain = "0";
        try{
            int Add = 0;
            int Del = 0;

            for (int i=0;i<mAddMoneys.size();i++){
                AddMoney addMoney = mAddMoneys.get(i);
                Add  = Add+(Integer.parseInt(addMoney.getAddMoney()));
            }

            for (int i=0;i<mPurchases.size();i++){
                Purchase purchase = mPurchases.get(i);
                Del  = Del+(Integer.parseInt(purchase.getCost()));
            }

            remain = (Add - Del)+"";
        }catch (Exception e){
            e.printStackTrace();
        }

        return remain;
    }
}
