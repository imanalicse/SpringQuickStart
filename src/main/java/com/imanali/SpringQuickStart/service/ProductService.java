package com.imanali.SpringQuickStart.service;

import com.imanali.SpringQuickStart.exception.RecordNotFoundException;
import com.imanali.SpringQuickStart.model.Product;
import com.imanali.SpringQuickStart.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> productsWithPagination(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        List<Product> products = productRepository.findAll(pageable).getContent();
        return products;
    }

    public Integer countProducts() {
        return Math.toIntExact(productRepository.count());
    }

    public Product getProduct(Long id) throws RecordNotFoundException {
        return productRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Product not found"));
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

    public void deleteProduct(Long id) throws RecordNotFoundException {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new RecordNotFoundException("Product does not exists");
        }
        productRepository.deleteById(id);
    }
}
