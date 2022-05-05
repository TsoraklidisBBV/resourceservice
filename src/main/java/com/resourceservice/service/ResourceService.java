package com.resourceservice.service;

import com.resourceservice.model.ResourceModel;
import com.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository repository;

    public ResourceModel getResourcesByName() throws IOException {
        List<ResourceModel> modelList = repository.findByUser("Dan");
        return findUser(modelList);
    }

    private ResourceModel findUser(List<ResourceModel> modelList) {
        if (modelList.isEmpty()) {
            System.out.println("Wrong name");
            return null;
        }

        List<ResourceModel> resource = new ArrayList<>();
        for (ResourceModel resourceModel : modelList) {
            if (resourceModel.getUser().equals("Dan")) {
                resource.add(resourceModel);
            }
        }

        if (resource.isEmpty()) {
            System.out.println("There is no user Dan");
            return null;
        }

        return resource.get(0);
    }

}
