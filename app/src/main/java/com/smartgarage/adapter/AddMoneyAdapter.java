package com.smartgarage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.smartgarage.R;
import com.smartgarage.bean.AddMoney;
import com.smartgarage.bean.Car;
import com.smartgarage.data.DBManger;

import java.util.ArrayList;
import java.util.List;

public class AddMoneyAdapter extends BaseAdapter{

    List<AddMoney> mAddMoneys = new ArrayList<>();
    Context mContext;
    public AddMoneyAdapter(Context context, List<AddMoney> mAddMoneys){
        this.mContext = context;
        this.mAddMoneys = mAddMoneys;
    }

    @Override
    public int getCount() {
        return mAddMoneys.size();
    }

    @Override
    public Object getItem(int position) {
        return mAddMoneys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final AddMoney addMoney = this.mAddMoneys.get(position);
        ViewHoler holer = null;
        if (view == null){
            holer = new ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.about_addmoney_item,null);
            holer.mAddTime = (TextView) view.findViewById(R.id.add_time);
            holer.mAddCost = (TextView) view.findViewById(R.id.add_cost);
            view.setTag(holer);
        }else{
            holer = (ViewHoler) view.getTag();
        }

        holer.mAddTime.setText(addMoney.getAddDate());
        holer.mAddCost.setText(addMoney.getAddMoney());

        return view;
    }

    class ViewHoler{
        TextView mAddTime;
        TextView mAddCost;
    }

}
