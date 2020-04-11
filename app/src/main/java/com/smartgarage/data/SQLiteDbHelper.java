package com.smartgarage.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SQLiteDbHelper extends SQLiteOpenHelper {

    //数据库名称
    public static final String DB_NAME = "SmartCar.db";
    //数据库版本号
    public static int DB_VERSION = 15;
    //用户表
    public static final String TAB_USER = "UserInfo";
    //用户角色表
    public static final String TAB_ROLE = "Role";
    //车辆信息表
    public static final String TAB_CARINFO = "CarInfo";
    //车库信息表
    public static final String TAB_CARPORTINFO = "CarPortInfo";
    //车位信息表
    public static final String TAB_PARKINGSAPCEINFO = "ParkingSpaceInfo";
    //卡信息表
    public static final String TAB_RFI = "RFIInfo";
    //停车记录信息表
    public static final String TAB_PARKINGINFO = "ParkingInfo";
    //消费表
    public static final String TAB_BILLINFO = "BillInfo";
    //路线信息表
    public static final String TAB_ROUTEINFO = "RouteInfo";
    //用户充值表
    public static final String TAB_ADDMONEYINFO = "AddMoneyInfo";

    public SQLiteDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableUser(db);
        createTableRole(db);
        createTableCarInfo(db);
        createTableCarPortInfo(db);
        createTableParkingSpaceInfo(db);
        createTableRFI(db);
        createTableParkingInfo(db);
        createTableBillInfo(db);
        createTableRouteInfo(db);
        createTableAddMoneyInfo(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TAB_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_ROLE);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_CARINFO);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_CARPORTINFO);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_PARKINGSAPCEINFO);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_RFI);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_PARKINGINFO);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_BILLINFO);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_ROUTEINFO);
        db.execSQL("DROP TABLE IF EXISTS "+TAB_ADDMONEYINFO);
        onCreate(db);
    }

    //创建用户表
    public void createTableUser(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_USER +
                "(UserId varchar(60) primary key, " +
                "UserName varchar(60), " +
                "Password varchar(60), " +
                "RFIId varchar(60), " +
                "RoleId integer, " +
                "Sex varchar(1), " +
                "Telephone varchar(60), " +
                "IdCard varchar(60), " +
                "UserPhoto text)");
    }

    //创建角色表
    public void createTableRole(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_ROLE +
                "(RoleId integer primary key autoincrement, " +
                "RolerName varchar(60))");
    }

    //创建车辆信息表
    public void createTableCarInfo(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_CARINFO +
                "(CarId varchar(60) primary key, " +
                "UserId varchar(60), " +
                "Type varchar(60), " +
                "CarPhoto text)");
    }

    //创建车库信息表
    public void createTableCarPortInfo(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_CARPORTINFO +
                "(CarPortId varchar(60) primary key, " +
                "CarPortName varchar(60), " +
                "Content integer, " +
                "IsFilled integer, " +
                "IsOrder integer, " +
                "RemainingNumber integer, " +
                "Address varchar(60), " +
                "Price varchar(60), " +
                "OrderPrice varchar(60), " +
                "Induction text, " +
                "CarPortPhoto text)");
    }

    //创建车位信息表
    public void createTableParkingSpaceInfo(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_PARKINGSAPCEINFO +
                "(PlaceId varchar(60) primary key, " +
                "CarPortId integer, " +
                "State varchar(60))");
    }

    //创建RFID卡信息表
    public void createTableRFI(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_RFI +
                "(RFIId varchar(60) primary key, " +
                "UserId varchar(60), " +
                "Remain varchar(60))");
    }

    //创建停车记录信息表
    public void createTableParkingInfo(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_PARKINGINFO +
                "(RecordId varchar(60) primary key, " +
                "CarPortId integer, " +
                "UserId varchar(60), " +
                "CarId varchar(60), " +
                "InTime varchar(60), " +
                "OutTime varchar(60), " +
                "ParkSumTime integer, " +
                "Cost varchar(60), " +
                "ParkingDate varchar(60), " +
                "Way varchar(60), " +
                "Status varchar(60))");
    }

    //创建消费表
    public void createTableBillInfo(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_BILLINFO +
                "(BillId varchar(60) primary key, " +
                "CarPortId varchar(60), " +
                "RecordId varchar(60), " +
                "UserId varchar(60), " +
                "BillDate varchar(60), " +
                "Cost varchar(60), " +
                "PayWay varchar(60))");
    }

    //创建路线信息表
    public void createTableRouteInfo(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_ROUTEINFO +
                "(RouteId varchar(60) primary key, " +
                "UserId varchar(60), " +
                "Point text, " +
                "RouteDate varchar(60), " +
                "RouteName varchar(60))");
    }

    //创建用户充值表
    public void createTableAddMoneyInfo(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TAB_ADDMONEYINFO +
                "(AddId varchar(60) primary key, " +
                "UserId varchar(60), " +
                "AddDate varchar(60), " +
                "AddMoney varchar(60), " +
                "PayWay varchar(60))");
    }

}
