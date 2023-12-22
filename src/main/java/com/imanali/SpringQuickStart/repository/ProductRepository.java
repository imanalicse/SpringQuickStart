package com.imanali.SpringQuickStart.repository;

import com.imanali.SpringQuickStart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByName(String name);
}
