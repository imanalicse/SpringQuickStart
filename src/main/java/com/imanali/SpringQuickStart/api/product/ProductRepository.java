package com.imanali.SpringQuickStart.api.product;

import com.imanali.SpringQuickStart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
