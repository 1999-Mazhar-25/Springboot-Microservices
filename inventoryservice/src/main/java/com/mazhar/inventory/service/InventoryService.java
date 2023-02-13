package com.mazhar.inventory.service;

import com.mazhar.inventory.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepo inventoryRepo;


    @Transactional(readOnly = true)
    public Boolean isSkuCode(String skuCode){
        return inventoryRepo.findBySkuCode(skuCode).isPresent();

    }


}
