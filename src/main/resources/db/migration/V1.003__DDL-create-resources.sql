CREATE TABLE TBL_RESOURCES
(
   id INT NOT NULL auto_increment,
   uuid VARCHAR (36) NOT NULL,
   name VARCHAR (250) NOT NULL,
   resource_class_entity_id INT NOT NULL,
   description VARCHAR (250) NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (resource_class_entity_id) REFERENCES TBL_CLASS_RESOURCE (id),
   CONSTRAINT TBL_RESOURCES_UUID UNIQUE (uuid)
);