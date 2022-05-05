package com.resourceservice.repository;

import com.resourceservice.model.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

    List<ResourceEntity> findByUuid(String uuid);

    List<ResourceEntity> deleteByUuid(String uuid);
}
