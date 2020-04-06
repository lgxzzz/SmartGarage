package com.smartgarage.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartgarage.FastNaviActivity;
import com.smartgarage.R;
import com.smartgarage.bean.PathInfo;
import com.smartgarage.bean.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PathAdapter extends BaseAdapter{

    List<PathInfo> mPathinfos = new ArrayList<>();
    Context mContext;
    public PathAdapter(Context context, List<PathInfo> mPathinfos){
        this.mContext = context;
        this.mPathinfos = mPathinfos;
    }

    @Override
    public int getCount() {
        return mPathinfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mPathinfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final PathInfo pathInfo = this.mPathinfos.get(position);
        ViewHoler holer = null;
        if (view == null){
            holer = new ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.path_item,null);
            holer.mPathTime = (TextView) view.findViewById(R.id.path_time);
            holer.mPathName = (TextView) view.findViewById(R.id.path_name);
            view.setTag(holer);
        }else{
            holer = (ViewHoler) view.getTag();
        }

        holer.mPathName.setText(pathInfo.getPathName());
        holer.mPathTime.setText(pathInfo.getTime());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext,FastNaviActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("carPort",pathInfo.getPort());
                b.putBoolean("isFastNavi",false);
                intent.putExtras(b);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHoler{
        TextView mPathName;
        TextView mPathTime;
    }

}
