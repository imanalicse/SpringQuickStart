package com.imanali.SpringQuickStart.model;

import com.imanali.SpringQuickStart.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false)
    private float price;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "categories_products",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"
            )
    )
    private List<Category> categories;

    public void addCategory(Category category) {
        if (categories == null) categories = new ArrayList<>();
        categories.add(category);
    }
}
