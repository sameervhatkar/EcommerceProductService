package dev.sameer.ecommerceproductservice.DTO;

import dev.sameer.ecommerceproductservice.Entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String title;
    private double price;
    private String description;
    private String categoryName;
    private double rating;
}
