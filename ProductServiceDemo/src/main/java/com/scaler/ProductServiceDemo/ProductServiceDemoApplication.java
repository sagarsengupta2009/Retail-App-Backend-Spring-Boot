package com.scaler.ProductServiceDemo;

import com.scaler.ProductServiceDemo.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductServiceDemoApplication.class, args);

		Product product = new Product();
	}

}
