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
import com.smartgarage.bean.ParkingSpaceInfo;
import com.smartgarage.data.DBManger;

import java.util.ArrayList;
import java.util.List;

public class ParkSpaceAdapter extends BaseAdapter{

    List<ParkingSpaceInfo> mParkSpaces = new ArrayList<>();
    Context mContext;
    IOrderListener listener;

    public ParkSpaceAdapter(Context context, List<ParkingSpaceInfo> mParkSpaces){
        this.mContext = context;
        this.mParkSpaces = mParkSpaces;
    }

    public void refreshData(ParkingSpaceInfo parkingSpaceInfo){
        this.mParkSpaces.add(parkingSpaceInfo);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mParkSpaces.size();
    }

    @Override
    public Object getItem(int position) {
        return mParkSpaces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ParkingSpaceInfo spaceInfo = this.mParkSpaces.get(position);
        ViewHoler holer = null;
        if (view == null){
            holer = new ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.park_item,null);
            holer.mParkID = (TextView) view.findViewById(R.id.park_id);
            holer.mParkState = (TextView) view.findViewById(R.id.park_state);
            holer.mParkOrder = (TextView) view.findViewById(R.id.park_order);
            view.setTag(holer);
        }else{
            holer = (ViewHoler) view.getTag();
        }

        holer.mParkID.setText(spaceInfo.getPlaceId());
        holer.mParkState.setText(spaceInfo.getState());
        holer.mParkOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParkSpaces.remove(spaceInfo);
                notifyDataSetChanged();
                if (listener!=null){
                    listener.onOrder(spaceInfo);
                }
            }
        });
        return view;
    }

    class ViewHoler{
        TextView mParkID;
        TextView mParkState;
        TextView mParkOrder;
    }

    public void setListener(IOrderListener listener) {
        this.listener = listener;
    }

    public interface  IOrderListener{
        public void onOrder(ParkingSpaceInfo spaceInfo);
    }
}
