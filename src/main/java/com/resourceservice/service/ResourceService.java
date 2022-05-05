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

    public ResourceModel getResourcesByName(String userName) throws IOException {
        List<ResourceModel> modelList = repository.findByUser(userName);
        return findUser(modelList, userName);
    }

    public void deleteResourcesByName(String userName) {
        // List<ResourceModel> modelList = repository.findByUser(userName);

        //  findUser(modelList, userName);
        //   Long deletedItems =
        repository.deleteByUser(userName);
//        if (deletedItems != 1){
//            return null;
//        }

        //   return true;
    }

    private ResourceModel findUser(List<ResourceModel> modelList, String userName) {

        if (modelList.isEmpty()) {
            System.out.println("Wrong name");
            return null;
        }

        List<ResourceModel> resource = new ArrayList<>();
        for (ResourceModel resourceModel : modelList) {
            if (resourceModel.getUser().equals(userName)) {
                resource.add(resourceModel);
            }
        }

        if (resource.isEmpty()) {
            System.out.println("There is no user" + userName);
            return null;
        }

        return resource.get(0);
    }
}
