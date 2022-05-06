package com.codesieucap.ueh_checkin.models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String idCode,email,userName,avatarImgUri;

    public UserModel(String idCode, String email, String userName, String avatarImgUri) {
        this.idCode = idCode;
        this.email = email;
        this.userName = userName;
        this.avatarImgUri = avatarImgUri;
    }

    public UserModel(){
        //default for retrieve data FireBase
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatarImgUri() {
        return avatarImgUri;
    }

    public void setAvatarImgUri(String avatarImgUri) {
        this.avatarImgUri = avatarImgUri;
    }
}
