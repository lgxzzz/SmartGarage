package com.smartgarage.bean;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarPort implements Serializable{

    String CarPortId;
    String CarPortName;
    int Content;
    int  IsFilled;
    int  IsOrder;
    int RemainingNumber;
    String Address;
    String Price;
    String OrderPrice;
    String Induction;
    double lat;
    double lon;
    List<ParkingSpaceInfo> mParkingSpaceInfos = new ArrayList();


    //返回一个空的车位
    public ParkingSpaceInfo getEmptyParkingSpaceInfo(){
        for (int i = 0;i<mParkingSpaceInfos.size();i++){
            ParkingSpaceInfo parkingSpaceInfo = mParkingSpaceInfos.get(i);
            if (parkingSpaceInfo.getState().equals("空")){
                return parkingSpaceInfo;
            }
        }
        return null;
    }

    public List<ParkingSpaceInfo> getmParkingSpaceInfos() {
        return mParkingSpaceInfos;
    }

    public void setmParkingSpaceInfos(List<ParkingSpaceInfo> mParkingSpaceInfos) {
        this.mParkingSpaceInfos = mParkingSpaceInfos;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCarPortId() {
        return CarPortId;
    }

    public void setCarPortId(String carPortId) {
        CarPortId = carPortId;
    }

    public String getCarPortName() {
        return CarPortName;
    }

    public void setCarPortName(String carPortName) {
        CarPortName = carPortName;
    }

    public int getContent() {
        return Content;
    }

    public void setContent(int content) {
        Content = content;
    }

    public int getIsFilled() {
        return IsFilled;
    }

    public void setIsFilled(int isFilled) {
        IsFilled = isFilled;
    }

    public int getIsOrder() {
        return IsOrder;
    }

    public void setIsOrder(int isOrder) {
        IsOrder = isOrder;
    }

    public int getRemainingNumber() {
        return RemainingNumber;
    }

    public void setRemainingNumber(int remainingNumber) {
        RemainingNumber = remainingNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getOrderPrice() {
        return OrderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        OrderPrice = orderPrice;
    }

    public String getInduction() {
        return Induction;
    }

    public void setInduction(String induction) {
        Induction = induction;
    }
}
