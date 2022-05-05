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

    String OwnerOfHardware;

    String uuid;

    String description;

    public ResourceEntity(Integer ID, String OwnerOfHardware, String description) {
        super();
        this.id = ID;
        this.OwnerOfHardware = OwnerOfHardware;
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

    public String getOwnerOfHardware() {
        return OwnerOfHardware;
    }

    public void setOwnerOfHardware(String user) {
        this.OwnerOfHardware = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String model) {
        this.description = model;
    }
}
