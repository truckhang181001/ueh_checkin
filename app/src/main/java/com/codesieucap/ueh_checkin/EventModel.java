package com.codesieucap.ueh_checkin;

public class EventModel {
    private Integer idCode;
    private String eventName, address, date, startTime, endTime, detail, avatarImgUri, coverImgUri;
    private JoinerModel[] listJoiner;

    public EventModel(Integer idCode, String eventName, String address, String date, String startTime, String endTime, String detail, String avatarImgUri, String coverImgUri, JoinerModel[] listJoiner) {
        this.idCode = idCode;
        this.eventName = eventName;
        this.address = address;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.detail = detail;
        this.avatarImgUri = avatarImgUri;
        this.coverImgUri = coverImgUri;
        this.listJoiner = listJoiner;
    }

    public Integer getIdCode() {
        return idCode;
    }

    public void setIdCode(Integer id) {
        this.idCode = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String name) {
        this.eventName = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAvatarImgUri() {
        return avatarImgUri;
    }

    public void setAvatarImgUri(String avatarImgUri) {
        this.avatarImgUri = avatarImgUri;
    }

    public String getCoverImgUri() {
        return coverImgUri;
    }

    public void setCoverImgUri(String coverImgUri) {
        this.coverImgUri = coverImgUri;
    }

    public JoinerModel[] getListJoiner() {
        return listJoiner;
    }

    public void setListJoiner(JoinerModel[] listJoiner) {
        this.listJoiner = listJoiner;
    }
}
