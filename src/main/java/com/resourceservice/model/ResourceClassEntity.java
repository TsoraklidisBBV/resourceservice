package com.resourceservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Table(name = "TBL_RESOURCE_CLASS")
@Entity
public class ResourceClassEntity {
    @Id
    @GeneratedValue
    Integer id;
    // technical value
//    @JoinTable(
//            name = "TBL_RESOURCES",
//            joinColumns = @JoinColumn(name = "resource_class_entity_uuid", referencedColumnName = "uuid"),
//            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "uuid")
//    )
    String uuid;
    // business value
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setName(String name) {
        this.name = name;
    }
}
