package com.imanali.SpringQuickStart.api.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private Long id;
    private String name;
    private float price;
    private LocalDateTime created_at;

    public Product() {
    }

    public Product(Long id, String name, float price, LocalDateTime created_at) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.created_at = created_at;
    }

    public Product(String name, float price, LocalDateTime created_at) {
        this.name = name;
        this.price = price;
        this.created_at = created_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", created_at=" + created_at +
                '}';
    }
}
