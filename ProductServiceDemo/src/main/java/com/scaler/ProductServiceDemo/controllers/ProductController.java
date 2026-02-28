package com.scaler.ProductServiceDemo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import com.scaler.ProductServiceDemo.models.Product;
import com.scaler.ProductServiceDemo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    //constructor func
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
//        ResponseEntity<Product> responseEntity = null;
//        try {
//            Product product = productService.getSingleProduct(id);
//            responseEntity = new ResponseEntity<>(
//                    product,
//                    HttpStatus.OK
//            );
//        } catch(RuntimeException e) {
//            responseEntity = new ResponseEntity<>(
//                    HttpStatus.NOT_FOUND
//            );
//        }

        ResponseEntity<Product> responseEntity = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );

        return responseEntity;
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(Long productId) {

    }

    @PatchMapping("/{id}")
    public Product updateProduct(
            @PathVariable("id") Long id,
            @RequestBody Product product
    ) {

        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }
}
