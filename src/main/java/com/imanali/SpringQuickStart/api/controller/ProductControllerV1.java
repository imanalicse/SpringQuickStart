package com.imanali.SpringQuickStart.api.controller;

import com.imanali.SpringQuickStart.api.response.DataResponseModel;
import com.imanali.SpringQuickStart.api.response.ResponseHandler;
import com.imanali.SpringQuickStart.exception.RecordNotFoundException;
import com.imanali.SpringQuickStart.model.Product;
import com.imanali.SpringQuickStart.repository.ProductRepository;
import com.imanali.SpringQuickStart.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@Tag(name = "Product")
@RequestMapping(path = "api/v1/products")
@PreAuthorize("hasRole('ADMIN')")
public class ProductControllerV1 {

    private ProductService productService;
    @GetMapping
    public ResponseEntity<DataResponseModel> getProducts() {
        List<Product> products = productService.getProducts();
        Map<String, Object> data = new HashMap<>();
        data.put("products", products);
        return ResponseHandler.oKResponse(data);
    }

    @GetMapping("/{page}/{limit}")
    public ResponseEntity<DataResponseModel> getProductWithPagination(@PathVariable int page, @PathVariable int limit) {
        List<Product> products = productService.productsWithPagination(page, limit);
        Integer total = productService.countProducts();
        Map<String, Object> data = new HashMap<>();
        data.put("products", products);

        HashMap<String, Integer> pagination = new HashMap<>();
        pagination.put("total", total);
        pagination.put("page", page);
        pagination.put("limit", limit);
        data.put("pagination", pagination);
        return ResponseHandler.oKResponse(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) throws RecordNotFoundException {
        Product product = productService.getProduct(id);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(product, headers, HttpStatus.OK);
    }

    @PostMapping
    public Product addProduct(@RequestBody @Valid Product product) {
        return productService.addNewProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
      Product product1 = productService.updateProduct(id, product);
      return  product1;
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) throws RecordNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
