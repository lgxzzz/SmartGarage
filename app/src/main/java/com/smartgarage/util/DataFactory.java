package com.smartgarage.util;

import android.content.Intent;

import com.smartgarage.bean.CarPort;

import java.util.HashMap;

public class DataFactory {
    public static CarPort createCarPortData(CarPort carPort){
        int Content = (int)(50*Math.random()+50);
        int IsFilled = (int)(10*Math.random()+10);
        int IsOrder = (int)(10*Math.random()+10);
        int RemainingNumber = Content - IsFilled -IsOrder;
        int Price = (int)(10*Math.random()+10);
        int OrderPrice = Price -1;
        carPort.setContent(Content);
        carPort.setIsFilled(IsFilled);
        carPort.setIsOrder(IsOrder);
        carPort.setRemainingNumber(RemainingNumber);
        carPort.setPrice(Price+"");
        carPort.setOrderPrice(OrderPrice+"");
        return carPort;
    }
}
