package com.example.assessment2.service;

import com.example.assessment2.exception.custom.MyCustomException;
import com.example.assessment2.model.Product;
import com.example.assessment2.model.UserCart;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.assessment2.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    private final CartRepository cartRepository;


    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void placeOrder(UserCart cart) {
        cartRepository.save(cart);
    }
    public UserCart viewMyCart(){
        UserCart cart = cartRepository.findAll().get(0);
        if(cart==null){
            throw new MyCustomException("Nothing to view in cart");
        }
        else{
            return cart;
        }
    }

    public void updateCart(UUID productId, String name, int quantity, double price){
        UserCart cart = viewMyCart();
        List<Product> prods = cart.getProduct();
        for(Product prod: prods){
            if(prod.getId() == productId){
                prod.setId(productId);
                prod.setName(name);
                prod.setQuantity(quantity);
                prod.setPrice(price);
            }
        }
    }
}
