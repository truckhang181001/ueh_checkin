package com.codesieucap.ueh_checkin.models;

public class JoinerModel {
    private Integer idCode;
    private String joinerName, className, email, ticketCode;
    private Boolean gender;
    private String status;

    public JoinerModel(Integer idCode, String joinerName, String className, String email, Boolean gender, String status, String ticketCode) {
        this.idCode = idCode;
        this.joinerName = joinerName;
        this.className = className;
        this.email = email;
        this.gender = gender;
        this.status = status;
        this.ticketCode = ticketCode;
    }

    public JoinerModel(){
        //default for retrieve data FireBase
    }

    public Integer getIdCode() {
        return idCode;
    }

    public void setIdCode(Integer id) {
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
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
}
