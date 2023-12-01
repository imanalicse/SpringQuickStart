package com.imanali.SpringQuickStart.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private String uuid;
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String image;
    private float price;
    private LocalDateTime created_at;

    public Product() {
    }

    public Product(Long id, String uuid, String name, float price, LocalDateTime created_at) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.created_at = created_at;
    }

    public Product(String name, float price, LocalDateTime created_at) {
        this.name = name;
        this.price = price;
        this.created_at = created_at;
    }

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", created_at=" + created_at +
                '}';
    }
}
