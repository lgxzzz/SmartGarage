package com.smartgarage.bean;

public class User {
    private String UserId;
    private String UserName;
    private String Password;
    private String RFIID;
    private String RepeatPassword;
    private int RoleId;
    private String Sex;
    private String Telephone;
    private String IdCard;
    private String UserPhoto;

    public String getRFIID() {
        return RFIID;
    }

    public void setRFIID(String RFIID) {
        this.RFIID = RFIID;
    }

    public String getRepeatPassword() {
        return RepeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        RepeatPassword = repeatPassword;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }
}
