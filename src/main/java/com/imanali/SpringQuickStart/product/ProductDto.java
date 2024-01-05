package com.imanali.SpringQuickStart.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String uuid;
    private String name;
    private String image;
    private float price;
}
