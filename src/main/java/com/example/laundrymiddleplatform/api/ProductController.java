package com.example.laundrymiddleplatform.api;

import com.example.laundrymiddleplatform.entities.Product;
import com.example.laundrymiddleplatform.response.ApiResponse;
import com.example.laundrymiddleplatform.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{Id}")
    public ResponseEntity<ApiResponse> getProductId(@PathVariable Long Id) throws Exception {
        Optional<Product> product = productService.getProductId(Id);
        if(product.isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get Product By Id Success")
                    .data(product)
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Get Product By Id Fail. Can't find product with id = " + Id)
                    .build());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productService.saveProduct(product);
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Product created successfully")
                    .data(savedProduct)
                    .build());
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Product created fail! Error: " + e.getMessage())
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if(products.isEmpty()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Fail to get all products!")
                    .build());
        }else {
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Fetched all products")
                    .data(products)
                    .build());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if(!productService.getProductId(id).isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Update fail! Can't find this product!")
                    .build());
        }else{
            product.setProductId(id);
            Product updatedProduct = productService.saveProduct(product);
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Product updated successfully")
                    .data(updatedProduct)
                    .build());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        if(!productService.getProductId(id).isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Delete fail! Can't find this product!")
                    .build());
        }else {
            productService.deleteProduct(id);
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Product deleted successfully")
                    .data(null)
                    .build());
        }
    }
}
