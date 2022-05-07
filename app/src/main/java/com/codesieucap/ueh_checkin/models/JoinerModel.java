package com.codesieucap.ueh_checkin.models;

import java.io.Serializable;
import java.util.UUID;

public class JoinerModel implements Serializable {
    private String idCode;
    private String joinerName, className, email, ticketCode,ticketCodeLink;
    private String gender;
    private String status;

    public static final String STATUS_SENT = "EMAIL_SENT";
    public static final String STATUS_FAIL = "SEND_FAIL";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_CHECKED = "CHECKED";


    public JoinerModel(String idCode, String joinerName, String className, String email, String gender) {
        this.idCode = idCode;
        this.joinerName = joinerName;
        this.className = className;
        this.email = email;
        this.gender = gender;
        this.status = STATUS_PENDING;
        this.ticketCode = UUID.randomUUID().toString();
    }

    public JoinerModel(){
        //default for retrieve data FireBase
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String id) {
        this.idCode = id;
    }

    public String getJoinerName() {
        return joinerName;
    }

    public void setJoinerName(String name) {
        this.joinerName = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getTicketCodeLink() {
        return ticketCodeLink;
    }

    public void setTicketCodeLink(String ticketCodeLink) {
        this.ticketCodeLink = ticketCodeLink;
    }
}
