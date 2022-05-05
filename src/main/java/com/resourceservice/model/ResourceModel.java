package com.resourceservice.model;

import java.util.Date;

public class ResourceModel {

    public ResourceModel(Integer ID, String user, String model) {
        super();
        this.ID = ID;
        this.user = user;
        this.model = model;
    }

    Integer ID;

    String user;

    String model;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
