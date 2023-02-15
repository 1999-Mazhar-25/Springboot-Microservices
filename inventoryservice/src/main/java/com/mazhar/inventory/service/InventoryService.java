package com.mazhar.inventory.service;

import com.mazhar.inventory.dto.InventoryResponse;
import com.mazhar.inventory.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    InventoryRepo inventoryRepo;


    @Transactional(readOnly = true)
    public List<InventoryResponse> isSkuCode(List<String> skuCode){
        return inventoryRepo.findBySkuCodeIn(skuCode).stream().map(inventory ->
            InventoryResponse.builder().skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0).build()
        ).collect(Collectors.toList());

    }


}
