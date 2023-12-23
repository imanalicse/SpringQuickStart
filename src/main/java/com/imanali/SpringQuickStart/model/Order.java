package com.imanali.SpringQuickStart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private float total;

    @ColumnDefault("0")
    private float shippingCost;

    @ColumnDefault("0")
    private float tax;

    private float gradTotal;

    @ColumnDefault("false")
    private boolean isTaxInclusivePrice;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;
}
