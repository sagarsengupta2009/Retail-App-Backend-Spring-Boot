package com.scaler.ProductServiceDemo.services;

import com.scaler.ProductServiceDemo.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);

    List<Product> getAllProducts();

    void deleteProduct(Long productId);

    Product updateProduct(Long productId, Product product);

    Product replaceProduct(Long productId, Product product);
}
