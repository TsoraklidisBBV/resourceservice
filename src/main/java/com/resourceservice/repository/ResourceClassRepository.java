package com.resourceservice.repository;

import com.resourceservice.model.ResourceClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceClassRepository extends JpaRepository<ResourceClassEntity, Long> {

    ResourceClassEntity save(ResourceClassEntity entity);

    List<ResourceClassEntity> findByUuid(String Uuid);

    Long deleteByUuid(String Uuid);
}
