package com.example.assessment2.controller;


import com.example.assessment2.exception.custom.MyCustomException;
import com.example.assessment2.model.Product;

import com.example.assessment2.service.ProductService;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ProductController {

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @PostMapping("/product")
    public ResponseEntity<String> addUser(@RequestBody Product product){
        logger.info("Adding a new product");
        productService.addProduct(product);
        return ResponseEntity.ok("product Added successfully!");
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable UUID id){
        Product product = null;
        try{
            product=productService.getProductById(id);
        }catch(IndexOutOfBoundsException ex){
            throw new MyCustomException("No product of id - " + id + "in storage");
        }
        return product;
    }

    @PutMapping("/product/update/{productId}")
    public ResponseEntity<String> updateUserDetails(@PathVariable UUID productId, @RequestParam String name, @RequestParam int quantity, @RequestParam double price) {
        try {
            productService.updateProductDetails(productId, name, quantity, price);
            return new ResponseEntity<>("User details updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user details. User not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("ShiftUser deleted successfully", HttpStatus.OK);
    }

}
