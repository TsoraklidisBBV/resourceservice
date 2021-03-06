package com.resourceservice.repository;

import com.resourceservice.model.ResourceClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceClassRepository extends JpaRepository<ResourceClassEntity, Long> {

    Optional<ResourceClassEntity> findByUuid(String Uuid);

    ResourceClassEntity getByUuid(String Uuid);

    Long deleteByUuid(String Uuid);
}
