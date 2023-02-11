package com.mazhar.product.controller;

import com.mazhar.product.dto.ProductRequest;
import com.mazhar.product.dto.ProductResponse;
import com.mazhar.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductRequest productRequest){

        productService.addProduct(productRequest);
    }


    @GetMapping("/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
