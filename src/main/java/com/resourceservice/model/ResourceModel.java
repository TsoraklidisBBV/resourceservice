package com.resourceservice.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TBL_RESOURCES")
@Entity
public class ResourceModel {

    @Id
    @GeneratedValue
    Integer id;

    String user;

    String model;

    public ResourceModel(Integer ID, String user, String model) {
        super();
        this.id = ID;
        this.user = user;
        this.model = model;
    }

    public ResourceModel() {

    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
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
