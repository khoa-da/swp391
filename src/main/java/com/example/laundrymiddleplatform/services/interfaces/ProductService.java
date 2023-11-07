package com.example.laundrymiddleplatform.services.interfaces;

import com.example.laundrymiddleplatform.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Product createProduct(String productName, String description, Integer price, int quantity, Long categoryId, String categoryName);

    Optional<Product> getProductId(Long productId);
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    void deleteProduct(Long id);
}
