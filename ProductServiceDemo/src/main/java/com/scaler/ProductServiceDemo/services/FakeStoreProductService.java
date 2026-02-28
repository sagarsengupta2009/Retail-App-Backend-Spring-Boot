package com.scaler.ProductServiceDemo.services;

import com.scaler.ProductServiceDemo.dtos.FakeStoreProductDto;
import com.scaler.ProductServiceDemo.dtos.FakeStoreUserDto;
import com.scaler.ProductServiceDemo.exceptions.ProductNotFoundException;
import com.scaler.ProductServiceDemo.models.Category;
import com.scaler.ProductServiceDemo.models.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) {

//        throw new ArithmeticException();

//        throw new RuntimeException("Not supported yet.");
        // Call FakeStore to fetch the product with given id. => HTTP Call.
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
        );

        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException("");
        }

        return convertFakeStoreProductToProduct(fakeStoreProductDto);


//        return new Product();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public void deleteProduct(Long productId) {
        restTemplate.delete(
                "https://fakestoreapi.com/products/" + productId
        );
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return updateProductInternal(productId, product, HttpMethod.PATCH);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
//        FakeStoreProductDto fakeStoreProductDto = restTemplate.put("https://fakestoreapi.com/products/" + productId, product);
        return updateProductInternal(productId, product, HttpMethod.PUT);
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

    private Product updateProductInternal(Long productId, Product product, HttpMethod method) {
        FakeStoreProductDto dto = new FakeStoreProductDto();
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription("");
        dto.setCategory(product.getCategory().getDescription()); // IMPORTANT
        RequestCallback requestCallback = restTemplate.httpEntityCallback(dto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(
                FakeStoreProductDto.class,
                restTemplate.getMessageConverters()
        );
        FakeStoreProductDto response = (FakeStoreProductDto) restTemplate.execute(
                "https://fakestoreapi.com/products/" + productId,
                method,
                requestCallback,
                responseExtractor
        );

        return convertFakeStoreProductToProduct(response);
    }
}
