package com.smartgarage.bean;

public class Purchase {
    String BillId;
    String CarPortId;
    String RecordId;
    String UserId;
    String BillDate;
    String Cost;
    String PayWay;
    String Time;

    CarPort carPort;
    Car car;
    ParkingSpaceInfo parkingSpaceInfo;

    public CarPort getCarPort() {
        return carPort;
    }

    public void setCarPort(CarPort carPort) {
        this.carPort = carPort;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ParkingSpaceInfo getParkingSpaceInfo() {
        return parkingSpaceInfo;
    }

    public void setParkingSpaceInfo(ParkingSpaceInfo parkingSpaceInfo) {
        this.parkingSpaceInfo = parkingSpaceInfo;
    }

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public String getCarPortId() {
        return CarPortId;
    }

    public void setCarPortId(String carPortId) {
        CarPortId = carPortId;
    }

    public String getRecordId() {
        return RecordId;
    }

    public void setRecordId(String recordId) {
        RecordId = recordId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public String getPayWay() {
        return PayWay;
    }

    public void setPayWay(String payWay) {
        PayWay = payWay;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
