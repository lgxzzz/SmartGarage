package com.smartgarage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.smartgarage.R;
import com.smartgarage.bean.Car;
import com.smartgarage.bean.Purchase;
import com.smartgarage.data.DBManger;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAdapter extends BaseAdapter{

    List<Purchase> mPurchases = new ArrayList<>();
    Context mContext;
    public PurchaseAdapter(Context context, List<Purchase> mPurchases){
        this.mContext = context;
        this.mPurchases = mPurchases;
    }

    @Override
    public int getCount() {
        return mPurchases.size();
    }

    @Override
    public Object getItem(int position) {
        return mPurchases.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Purchase purchase = this.mPurchases.get(position);
        ViewHoler holer = null;
        if (view == null){
            holer = new ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.about_purchase_his_item,null);
            holer.mPurchaseTime = (TextView) view.findViewById(R.id.purchase_time);
            holer.mPurchaseCost = (TextView) view.findViewById(R.id.purchase_cost);
            holer.mPurchaseDetail = (TextView) view.findViewById(R.id.purchase_detail);
            view.setTag(holer);
        }else{
            holer = (ViewHoler) view.getTag();
        }

        holer.mPurchaseTime.setText(purchase.getBillDate());
        holer.mPurchaseCost.setText(purchase.getCost()+"元");
        holer.mPurchaseDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    class ViewHoler{
        TextView mPurchaseTime;
        TextView mPurchaseCost;
        TextView mPurchaseDetail;
    }

}
