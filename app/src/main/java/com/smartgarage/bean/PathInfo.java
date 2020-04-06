package com.smartgarage.bean;

public class PathInfo {
    public String pathName;
    public String time;
    public CarPort port;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CarPort getPort() {
        return port;
    }

    public void setPort(CarPort port) {
        this.port = port;
    }
}
