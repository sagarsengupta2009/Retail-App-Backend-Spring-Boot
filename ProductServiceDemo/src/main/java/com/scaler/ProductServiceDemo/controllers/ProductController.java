package com.scaler.ProductServiceDemo.controllers;

import com.scaler.ProductServiceDemo.models.Product;
import com.scaler.ProductServiceDemo.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    //constructor func
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(Long productId) {

    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @PathVariable("product") Product product) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(Long id, Product product) {
        return productService.replaceProduct(id, product);
    }
}
