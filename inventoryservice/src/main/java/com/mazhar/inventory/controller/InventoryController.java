package com.mazhar.inventory.controller;

import com.mazhar.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    public boolean isInStock(@PathVariable ("sku-code") String skuCode){

        return inventoryService.isSkuCode(skuCode);
    }
}
