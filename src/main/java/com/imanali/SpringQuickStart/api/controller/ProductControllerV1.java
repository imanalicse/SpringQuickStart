package com.imanali.SpringQuickStart.api.controller;

import com.imanali.SpringQuickStart.model.Product;
import com.imanali.SpringQuickStart.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;


@RestController
@RequestMapping(path = "api/v1/products")
@AllArgsConstructor
@Tag(name = "Product")
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
        Product product1 = new Product();
        product1.setImage("aaa");
        product1.setName("PC");

        ArrayList<String> names = new ArrayList<>(Arrays.asList("Ismail", "Ishaq"));
        names.add("Iman");
        names.add("Ishak");

        HashMap<String, String>  errors = new HashMap<>();
        errors.put("username", "Invalid username");
        errors.put("password", "Invalid password");

        HashMap<String, String> error_response = new HashMap<>();
        error_response.put("status_code", "400");
        error_response.put("message", Arrays.asList(errors).toString());

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(product, headers, HttpStatus.OK);
    }

/*
    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.getProduct(id);
        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product;
    }
*/

/*
    @PostMapping
    public Product addProduct(@RequestBody @Valid Product product) {
        return productService.addNewProduct(product);
    }
*/

    @PostMapping
    public Product addProduct(@RequestParam String name, @RequestParam Float price, @RequestParam MultipartFile imageFile) {

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImage(imageFile.getOriginalFilename());
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
