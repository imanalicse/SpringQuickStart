package com.imanali.SpringQuickStart.api.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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

    public Product getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity no found"));
        return product;
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

    public void updateProduct(Long id, Product product) {
        productRepository.findById(id)
                .map(product1 -> {
                    product1.setName(product.getName());
                    product1.setPrice(product.getPrice());
                    return productRepository.save(product1);
                })
                .orElseGet(() -> {
//                        product.setId(id);
//                        return productRepository.save(product);
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
