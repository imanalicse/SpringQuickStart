package com.imanali.SpringQuickStart.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        UUID uuid = UUID.randomUUID();
        product.setUuid(uuid);
        product.setCreated_at(LocalDateTime.now());
        System.out.println(product);
        if (product.getPrice() < 0) {
            throw new IllegalStateException("Price cannot be negative");
        }
        productRepository.save(product);
    }
}
