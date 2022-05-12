package com.resourceservice.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "TBL_RESOURCES")
@Entity
public class ResourceEntity {

    @Id
    @GeneratedValue
    Integer id;

    String owner;

    String uuid;

    //uuid of the hardware as well ?

    String description;

    public ResourceEntity(Integer ID, String owner, String description) {
        super();
        this.id = ID;
        this.owner = owner;
        this.description = description;
    }

    public ResourceEntity() {

    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String user) {
        this.owner = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String model) {
        this.description = model;
    }
}
