package com.example.assessment2.controller;

import com.example.assessment2.exception.custom.MyCustomException;
import com.example.assessment2.model.Product;
import com.example.assessment2.model.UserCart;
import com.example.assessment2.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CartController {
    private final CartService cartService;


    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }
    private final Logger logger = LoggerFactory.getLogger(CartController.class);
    @PostMapping("/add")
    public ResponseEntity<String> placeOrder(@RequestBody UserCart cart){
        logger.info("Adding to cart");
        cartService.placeOrder(cart);
        return ResponseEntity.ok("product Added successfully!");
    }

    /**
     * @return products placed inside a Cart
     */
    @GetMapping("/cart")
    public List<Product> getProduct(){
        UserCart cart = null;
        try{
            cart=cartService.viewMyCart();
        }catch(Exception ex){
            logger.error("Cart service not available");
            throw new MyCustomException("Sorry for inconvenience!!");
        }
        return cart.getProduct();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCart(@RequestParam UUID productId, @RequestParam String name, @RequestParam int quantity, @RequestParam double price) {
        try {
            cartService.updateCart(productId, name, quantity, price);
            return new ResponseEntity<>("User details updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user details. User not found.", HttpStatus.NOT_FOUND);
        }
    }
}