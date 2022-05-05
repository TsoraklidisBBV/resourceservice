package com.resourceservice.repository;

import com.resourceservice.model.ResourceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceModel, Long> {

    List<ResourceModel> findByUser(String user);

    List<ResourceModel> deleteByUser(String user);
}
