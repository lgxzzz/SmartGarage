package com.smartgarage.data;

import android.content.Context;

import com.smartgarage.bean.User;

public class DBManger {
    private Context mContext;
    private SQLiteDbHelper mDBHelper;

    public static  DBManger instance;

    public static DBManger getInstance(Context mContext){
        if (instance == null){
            instance = new DBManger(mContext);
        }
        return instance;
    };

    public DBManger(Context mContext){
        this.mContext = mContext;
        mDBHelper = new SQLiteDbHelper(mContext);
    }

    public void registerUser(User user,IRegisterListener listener){
        listener.onSuccess();
    };

    public interface IRegisterListener{
        public void onSuccess();
        public void onError(int error);
    };
}
