package com.smartgarage.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.smartgarage.R;
import com.smartgarage.bean.Car;
import com.smartgarage.data.DBManger;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends BaseAdapter{

    List<Car> mCarInfos = new ArrayList<>();
    Context mContext;
    public CarAdapter(Context context,List<Car> mCarInfos){
        this.mContext = context;
        this.mCarInfos = mCarInfos;
    }

    @Override
    public int getCount() {
        return mCarInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mCarInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Car car = this.mCarInfos.get(position);
        ViewHoler holer = null;
        if (view == null){
            holer = new ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.about_car_item,null);
            holer.mCarID = (TextView) view.findViewById(R.id.car_id);
            holer.mCarType = (TextView) view.findViewById(R.id.car_type);
            holer.mCarDelete = (TextView) view.findViewById(R.id.delete_car_btn);
            view.setTag(holer);
        }else{
            holer = (ViewHoler) view.getTag();
        }

        holer.mCarID.setText(car.getCarId());
        holer.mCarType.setText(car.getType());
        holer.mCarDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManger.getInstance(mContext).deleteCar(car, new DBManger.IListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(mContext,"删除成功",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
                mCarInfos.remove(car);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    class ViewHoler{
        TextView mCarID;
        TextView mCarType;
        TextView mCarDelete;
    }

}
