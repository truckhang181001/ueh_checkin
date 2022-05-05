package com.codesieucap.ueh_checkin.ui.participant;

public class Participant {

    private String order;
    private String name;
    private String branch;
    private String status;

    public Participant(String order, String name, String branch, String status) {
        this.order = order;
        this.name = name;
        this.branch = branch;
        this.status = status;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
