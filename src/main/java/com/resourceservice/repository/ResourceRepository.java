package com.resourceservice.repository;

import com.resourceservice.model.ResourceClassEntity;
import com.resourceservice.model.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

    Optional<ResourceEntity> findByUuid(String uuid);

    Long deleteByUuid(String uuid);
}
