package com.smartgarage.bean;

public class CarPort {

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
