package com.smartgarage.view;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;
import com.smartgarage.FastNaviActivity;
import com.smartgarage.R;
import com.smartgarage.bean.CarPort;
import java.util.Calendar;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker.OnTimeChangedListener;
public class SelectDateDialog extends Dialog {

    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private View view;
    private Context context;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    public IOnSelectListener getListener() {
        return listener;
    }

    public void setListener(IOnSelectListener listener) {
        this.listener = listener;
    }

    IOnSelectListener listener;

    public SelectDateDialog(Context context, int layoutid, boolean isCancelable, boolean isBackCancelable) {
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

    public void initView() {
        datePicker = view.findViewById(R.id.select_date);
        timePicker = view.findViewById(R.id.select_time);

        //获取当前的年、月、日、小时、分钟

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        //初始化datePicker组件，并指定监听器
        datePicker.init(year, month, day, new OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                if (listener!=null){
                    listener.onSelectDate(year+"."+month+"."+day);
                }
            }
        });

        //初始化timePicker组件指定监听器

        timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hour, int minute) {
                if (listener!=null){
                    listener.onSelectTime(hour+"."+minute);
                }
            }
        });

    }

    public void setSelectDate(boolean flag){
        if (flag){
            datePicker.setVisibility(View.VISIBLE);
            timePicker.setVisibility(View.GONE);
        }else{
            datePicker.setVisibility(View.GONE);
            timePicker.setVisibility(View.VISIBLE);
        }
    }

    public interface IOnSelectListener{
        public void onSelectDate(String date);
        public void onSelectTime(String time);
    }
}