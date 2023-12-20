package com.imanali.SpringQuickStart.service;

import com.imanali.SpringQuickStart.model.Product;
import com.imanali.SpringQuickStart.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        log.info("getProducts was called");
        try {
            return productRepository.findAll();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public Product addNewProduct(Product product) {
        product.setUuid(UUID.randomUUID().toString());
        System.out.println(product);
        if (product.getPrice() < 0) {
            throw new IllegalStateException("Price cannot be negative");
        }
       return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
      return productRepository.findById(id)
                .map(product1 -> {
                    product1.setName(product.getName());
                    product1.setPrice(product.getPrice());
                    return productRepository.save(product1);
                })
                .orElseGet(() -> {
                        throw new IllegalStateException("Product not found with id "+ id);
                });
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Product with id " + id + " does not exists");
        }
        productRepository.deleteById(id);
    }
}
