package com.resourceservice.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "TBL_RESOURCES")
@Entity
public class ResourceEntity {

    @Id
    @GeneratedValue
    Integer id;

    String name;

    String uuid;

    @ManyToOne
    @JoinColumn(name = "resource_class_entity_id")
    ResourceClassEntity resourceClassEntity;
    String description;

    public ResourceClassEntity getResourceClassEntity() {
        return resourceClassEntity;
    }

    public void setResourceClassEntity(ResourceClassEntity resourceClassEntity) {
        this.resourceClassEntity = resourceClassEntity;
    }

    public ResourceEntity() {

    }

    public ResourceEntity(Integer id, String name, String uuid, String description, ResourceClassEntity resourceClassEntity) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
        this.description = description;
        this.resourceClassEntity = resourceClassEntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String user) {
        this.name = user;
    }


}
