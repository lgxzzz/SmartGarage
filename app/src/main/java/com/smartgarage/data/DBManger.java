package com.smartgarage.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.amap.api.maps.model.LatLng;
import com.smartgarage.bean.AddMoney;
import com.smartgarage.bean.Car;
import com.smartgarage.bean.CarPort;
import com.smartgarage.bean.PathInfo;
import com.smartgarage.bean.Purchase;
import com.smartgarage.bean.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DBManger {
    private Context mContext;
    private SQLiteDbHelper mDBHelper;
    public User mUser;
    //最终选择的点
    private LatLng mCurPoint;
    //车库信息
    public List<CarPort> mCarPorts = new ArrayList<>();
    //路径信息
    public List<PathInfo> mPaths = new ArrayList<>();
    //当前的预定信息
    Purchase mOrderPurchase;

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

    public Purchase getmOrderPurchase() {
        return mOrderPurchase;
    }

    public void setmOrderPurchase(Purchase mOrderPurchase,IListener listener) {
        this.mOrderPurchase = mOrderPurchase;
        insertPurchase(mOrderPurchase,listener);
    }

    public List<PathInfo> getmPaths() {
        return mPaths;
    }

    public void setmPaths(List<PathInfo> mPaths) {
        this.mPaths = mPaths;
    }

    public LatLng getmCurPoint() {
        return mCurPoint;
    }

    public void setmCurPoint(LatLng mCurPoint) {
        this.mCurPoint = mCurPoint;
    }

    public List<CarPort> getmCarPorts() {
        return mCarPorts;
    }

    public void setmCarPorts(List<CarPort> mCarPorts) {
        this.mCarPorts = mCarPorts;
    }

    //生成随机15位的user编号
    public String getRandomUserID(){
        String strRand="" ;
        for(int i=0;i<15;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    //生成随机10位的RFI编号
    public String getRandomRFIID(){
        String strRand="" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    //生成随机10位的RFI编号
    public String getRandomBILLID(){
        String strRand="" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    //添加车辆
    public void insertCar(Car car,IListener listener){
        try{
            ContentValues values = new ContentValues();
            values.put("CarId",car.getCarId());
            values.put("UserId",car.getUserId());
            values.put("Type",car.getType());
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            long code = db.insert(SQLiteDbHelper.TAB_CARINFO,null,values);
            listener.onSuccess();
        }catch (Exception e){
            listener.onError("");
            e.printStackTrace();
        }
    }

    //添加充值信息
    public void insertAddMoney(AddMoney addMoney,IListener listener){
        try{
            ContentValues values = new ContentValues();
            values.put("AddId",addMoney.getAddId());
            values.put("UserId",addMoney.getUserId());
            values.put("AddDate",addMoney.getAddDate());
            values.put("AddMoney",addMoney.getAddMoney());
            values.put("PayWay",addMoney.getPayWay());
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            long code = db.insert(SQLiteDbHelper.TAB_ADDMONEYINFO,null,values);
            listener.onSuccess();
        }catch (Exception e){
            listener.onError("");
            e.printStackTrace();
        }
    }

    public List<AddMoney> getAddMoneys(){
        List<AddMoney> addMoneys = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query(SQLiteDbHelper.TAB_ADDMONEYINFO,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String AddId = cursor.getString(cursor.getColumnIndex("AddId"));
                String UserId = cursor.getString(cursor.getColumnIndex("UserId"));
                String AddDate = cursor.getString(cursor.getColumnIndex("AddDate"));
                String AddMoney = cursor.getString(cursor.getColumnIndex("AddMoney"));
                String PayWay = cursor.getString(cursor.getColumnIndex("PayWay"));

                AddMoney addMoney = new AddMoney();
                addMoney.setAddId(AddId);
                addMoney.setUserId(UserId);
                addMoney.setAddDate(AddDate);
                addMoney.setAddMoney(AddMoney);
                addMoney.setPayWay(PayWay);

                addMoneys.add(addMoney);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return addMoneys;
    }

    //用户注册时生成一张RFI卡
    public String createRFIInfo(String userID){
        try{
            String RFIID = getRandomRFIID();
            ContentValues values = new ContentValues();
            values.put("UserId",userID);
            values.put("Remain","0");
            values.put("RFIId",RFIID);
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            long code = db.insert(SQLiteDbHelper.TAB_RFI,null,values);
            return RFIID;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //注册用户
    public void registerUser(User user,IListener listener){
        try{
            String UserId = getRandomUserID();
            String RFIID = createRFIInfo(UserId);
            ContentValues values = new ContentValues();
            values.put("UserId",UserId);
            values.put("UserName",user.getUserName());
            values.put("Password",user.getPassword());
            values.put("RFIId",RFIID);
            values.put("RoleId",user.getRoleId());
            values.put("Sex",user.getSex());
            values.put("IdCard",user.getIdCard());
            values.put("Telephone",user.getTelephone());
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            long code = db.insert(SQLiteDbHelper.TAB_USER,null,values);
            listener.onSuccess();
        }catch (Exception e){
            e.printStackTrace();
        }

    };

    //用户登陆
    public void login(String name,String password,IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from UserInfo where UserName =? and Password=?",new String[]{name,password});
            if (cursor.moveToFirst()){
                String UserId = cursor.getString(cursor.getColumnIndex("UserId"));
                String UserName = cursor.getString(cursor.getColumnIndex("UserName"));
                int RoleId = cursor.getInt(cursor.getColumnIndex("RoleId"));
                String Sex = cursor.getString(cursor.getColumnIndex("Sex"));
                String Telephone = cursor.getString(cursor.getColumnIndex("Telephone"));
                String IdCard = cursor.getString(cursor.getColumnIndex("IdCard"));
                String RFIId = cursor.getString(cursor.getColumnIndex("RFIId"));

                mUser = new User();
                mUser.setUserId(UserId);
                mUser.setUserName(UserName);
                mUser.setRoleId(RoleId);
                mUser.setSex(Sex);
                mUser.setTelephone(Telephone);
                mUser.setIdCard(IdCard);
                mUser.setRFIID(RFIId);

                listener.onSuccess();
            }else{
                listener.onError("未查询到该用户");
            }
            db.close();
            return;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        listener.onError("未查询到该用户");
    }

    //修改用户信息
    public void updateUser(User user,IListener listener){
        try{
            ContentValues values = new ContentValues();
            values.put("UserName",user.getUserName());
            values.put("Sex",user.getSex());
            values.put("IdCard",user.getIdCard());
            values.put("Telephone",user.getTelephone());
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            int code = db.update(SQLiteDbHelper.TAB_USER,values,"UserId =?",new String[]{user.getUserId()+""});
            listener.onSuccess();
        }catch (Exception e){

        }
    }

    public List<Car> getAllCars(){
        List<Car> cars = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query(SQLiteDbHelper.TAB_CARINFO,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String CarId = cursor.getString(cursor.getColumnIndex("CarId"));
                String UserId = cursor.getString(cursor.getColumnIndex("UserId"));
                String Type = cursor.getString(cursor.getColumnIndex("Type"));
                Car car = new Car();
                car.setCarId(CarId);
                car.setUserId(UserId);
                car.setType(Type);
                cars.add(car);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cars;
    }

    public void deleteCar(Car car,IListener listener){
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            int code = db.delete(SQLiteDbHelper.TAB_CARINFO,"CarId =?",new String[]{car.getCarId()+""});
            Log.e("lgx","");
            listener.onSuccess();
        }catch (Exception e){

        }
    }

    //生成默认消费记录和详单记录
    public List<Purchase> getDefaultBillData(){
        List<Purchase> purchases  = new ArrayList<>();
        for (int i=0;i<10;i++){
            Purchase purchase = new Purchase();
            purchase.setUserId(mUser.getUserId());
            purchase.setBillId(getRandomBILLID());
            purchase.setBillDate(getRandomBiiDate());
//            purchase.setCarPortId();
            purchase.setCost(getRandomCost()+"元");
            purchase.setPayWay("现金");//现金-M行卡-C 会员卡-V
//            purchase.setRecordId();

            purchases.add(purchase);
        }
        return purchases;
    };

    //添加车辆
    public void insertPurchase(Purchase purchase,IListener listener){
        try{
            ContentValues values = new ContentValues();
            values.put("CarPortId",purchase.getCarPortId());
            values.put("BillId",purchase.getBillId());
            values.put("RecordId",purchase.getRecordId());
            values.put("UserId",purchase.getUserId());
            values.put("BillDate",purchase.getBillDate());
            values.put("Cost",purchase.getCost());
            values.put("PayWay",purchase.getPayWay());
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            long code = db.insert(SQLiteDbHelper.TAB_BILLINFO,null,values);
            listener.onSuccess();
        }catch (Exception e){
            listener.onError("");
            e.printStackTrace();
        }
    }

    public List<Purchase> getPurchases(){
        List<Purchase> purchases = new ArrayList<>();
        try{
            SQLiteDatabase db = mDBHelper.getWritableDatabase();
            Cursor cursor = db.query(SQLiteDbHelper.TAB_BILLINFO,null,null,null,null,null,null);
            while (cursor.moveToNext()){
                String BillId = cursor.getString(cursor.getColumnIndex("BillId"));
                String CarPortId = cursor.getString(cursor.getColumnIndex("CarPortId"));
                String RecordId = cursor.getString(cursor.getColumnIndex("RecordId"));
                String UserId = cursor.getString(cursor.getColumnIndex("UserId"));
                String BillDate = cursor.getString(cursor.getColumnIndex("BillDate"));
                String Cost = cursor.getString(cursor.getColumnIndex("Cost"));
                String PayWay = cursor.getString(cursor.getColumnIndex("PayWay"));

                Purchase purchase = new Purchase();
                purchase.setUserId(UserId);
                purchase.setBillId(BillId);
                purchase.setBillDate(BillDate);
                purchase.setCarPortId(CarPortId);
                purchase.setCost(Cost);
                purchase.setPayWay(PayWay);//现金-M行卡-C 会员卡-V
                purchases.add(purchase);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return purchases;
    }

    //生成默认充值金额
    public List<AddMoney> getDefaultAddMoneyData(){
        List<AddMoney> AddMoneys  = new ArrayList<>();
        for (int i=0;i<10;i++){
            AddMoney addMoney = new AddMoney();
            addMoney.setUserId(mUser.getUserId());
            addMoney.setAddDate(getRandomBiiDate());
            addMoney.setAddId(getRandomADDID());
            addMoney.setAddMoney(getRandomCost()+"元");
            addMoney.setPayWay("现金");
            AddMoneys.add(addMoney);
        }
        return AddMoneys;
    };

    //生成随机10位的充值编号
    public String getRandomADDID(){
        String strRand="" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    //随机生成账单日期
    public String getRandomBiiDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    //随机生成消费金额
    public String getRandomCost(){
        String strRand="" ;
        for(int i=0;i<2;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    public interface IListener{
        public void onSuccess();
        public void onError(String error);
    };


}
