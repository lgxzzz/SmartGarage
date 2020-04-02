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
import com.smartgarage.bean.CarPort;
import com.smartgarage.data.DBManger;

import java.util.ArrayList;
import java.util.List;

public class CarPortAdapter extends BaseAdapter{

    List<CarPort> mCarPorts = new ArrayList<>();
    Context mContext;
    public CarPortAdapter(Context context,List<CarPort> mCarPorts){
        this.mContext = context;
        this.mCarPorts = mCarPorts;
    }

    @Override
    public int getCount() {
        return mCarPorts.size();
    }

    @Override
    public Object getItem(int position) {
        return mCarPorts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final CarPort carport = this.mCarPorts.get(position);
        ViewHoler holer = null;
        if (view == null){
            holer = new ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.carport_item,null);
            holer.mName = (TextView) view.findViewById(R.id.carport_name);
            holer.mAddress = (TextView) view.findViewById(R.id.carport_address);
            view.setTag(holer);
        }else{
            holer = (ViewHoler) view.getTag();
        }

        holer.mName.setText(carport.getCarPortName());
        holer.mAddress.setText(carport.getAddress());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    class ViewHoler{
        TextView mName;
        TextView mAddress;
    }

}
