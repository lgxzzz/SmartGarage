package com.smartgarage.bean;

import java.io.Serializable;

public class ParkingSpaceInfo implements Serializable {
    String PlaceId;
    String CarPortId;
    String State;

    public String getPlaceId() {
        return PlaceId;
    }

    public void setPlaceId(String placeId) {
        PlaceId = placeId;
    }

    public String getCarPortId() {
        return CarPortId;
    }

    public void setCarPortId(String carPortId) {
        CarPortId = carPortId;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}
