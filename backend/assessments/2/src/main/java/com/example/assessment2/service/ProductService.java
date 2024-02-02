package com.example.assessment2.service;

import com.example.assessment2.model.Product;
import com.example.assessment2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ProductService {
    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(UUID productId) {
        return productRepository.findById(productId).orElse(null);
    }

   public void updateProductDetails(UUID productId, String name, int quantity, double price){
    productRepository.updateUserDetails(productId, name, quantity, price);
   }

    public void deleteProduct(UUID productId){
        productRepository.deleteById(productId);
    }

}
