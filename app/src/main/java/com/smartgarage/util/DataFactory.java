package com.smartgarage.util;

import android.content.Intent;

import com.smartgarage.bean.CarPort;
import com.smartgarage.bean.ParkingSpaceInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataFactory {
    public static CarPort createCarPortData(CarPort carPort){
        //随机生成车库信息
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
        //随机生成车位信息
        List<ParkingSpaceInfo> mParkingSpaceInfos = createParkingSpaceInfos(carPort);
        carPort.setmParkingSpaceInfos(mParkingSpaceInfos);
        return carPort;
    }

    //随机生成车位信息
    public static List<ParkingSpaceInfo> createParkingSpaceInfos(CarPort carPort){
        List<ParkingSpaceInfo> mParkingSpaceInfos = new ArrayList();
        //总数
        int Content = carPort.getContent();

        //已停数量
        int IsFilled = carPort.getIsFilled();
        int tempFill = 0;

        //预定数量
        int IsOrder = carPort.getIsOrder();
        int tempOrder = 0;

        for (int i=0;i<Content;i++){
            ParkingSpaceInfo park = new ParkingSpaceInfo();
            park.setCarPortId(carPort.getCarPortId());
            park.setPlaceId(getRandomParkID());
            if (tempFill!=IsFilled){
                tempFill++;
                park.setState("已停");
            }else if(tempOrder!=IsOrder){
                tempOrder++;
                park.setState("已预定");
            }else{
                park.setState("空");
            }
        }
        return mParkingSpaceInfos;
    }

    //生成随机10位的车位ID
    public static String getRandomParkID(){
        String strRand="P" ;
        for(int i=0;i<5;i++){
            strRand += String.valueOf((int)(Math.random() * 5)) ;
        }
        return strRand;
    }
}
