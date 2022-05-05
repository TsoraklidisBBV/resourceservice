package com.resourceservice.repository;

import com.resourceservice.model.ResourceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<ResourceModel, Integer> {

    List<ResourceModel> findByUser(String user);
}
