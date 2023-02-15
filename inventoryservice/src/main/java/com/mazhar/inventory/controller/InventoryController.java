package com.mazhar.inventory.controller;

import com.mazhar.inventory.dto.InventoryResponse;
import com.mazhar.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){

        return inventoryService.isSkuCode(skuCode);
    }
}
