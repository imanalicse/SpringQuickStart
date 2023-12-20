package com.imanali.SpringQuickStart.api.controller;

import com.imanali.SpringQuickStart.model.Product;
import com.imanali.SpringQuickStart.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@RestController
@AllArgsConstructor
@Tag(name = "Product")
@RequestMapping(path = "api/v1/products")
@PreAuthorize("hasRole('ADMIN')")
public class ProductControllerV1 {

    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        if (products == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProduct(id);
        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(product, headers, HttpStatus.OK);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
      Product product1 = productService.updateProduct(id, product);
      return  product1;
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
