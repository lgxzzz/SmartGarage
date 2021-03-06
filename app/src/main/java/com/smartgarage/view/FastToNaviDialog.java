package com.smartgarage.view;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.smartgarage.FastNaviActivity;
import com.smartgarage.R;
import com.smartgarage.bean.CarPort;

import org.w3c.dom.Text;


public class FastToNaviDialog extends Dialog {

    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private View view;
    private Context context;
    private TextView mParkspcaceIDTv;
    private Button mSureBtn;
    private Button mCancelBtn;
    private CarPort mCarPort;

    public FastToNaviDialog(Context context, int layoutid, boolean isCancelable, boolean isBackCancelable) {
        super(context, R.style.MyDialog);

        this.context = context;
        this.view = LayoutInflater.from(context).inflate(layoutid, null);
        this.iscancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;

        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);//这行一定要写在前面
        setCancelable(iscancelable);//点击外部不可dismiss
        setCanceledOnTouchOutside(isBackCancelable);


    }

    public void setData(CarPort carPort){
        this.mCarPort = carPort;
        mParkspcaceIDTv.setText(carPort.getEmptyParkingSpaceInfo().getPlaceId());
    }

    public void initView() {
        mParkspcaceIDTv = view.findViewById(R.id.parking_id);
        mSureBtn = view.findViewById(R.id.fast_navi_sure_btn);
        mCancelBtn = view.findViewById(R.id.fast_navi_cancel_btn);

        mSureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent intent = new Intent();
                intent.setClass(getContext(),FastNaviActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("carPort",mCarPort);
                b.putBoolean("isFastNavi",true);
                intent.putExtras(b);
                getContext().startActivity(intent);

            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}