package com.codesieucap.ueh_checkin;

public class UserModel {
    private String email, password, userName, address, avatarImgUri,idCode;
    private Boolean gender;
    private Integer phone;

    public UserModel(String idCode,String email, String password, String userName, String address, Boolean gender, Integer phone, String avatarImgUri) {
        this.idCode = idCode;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
        this.avatarImgUri = avatarImgUri;
    }

    public UserModel(){
        //default for retrieve data FireBase
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String id) {
        this.idCode = id;
    }

    public String getAvatarImgUri() {
        return avatarImgUri;
    }

    public void setAvatarImgUri(String avatarImgUri) {
        this.avatarImgUri = avatarImgUri;
    }
}
