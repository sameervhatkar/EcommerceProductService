package dev.sameer.ecommerceproductservice.DTO;

import dev.sameer.ecommerceproductservice.Entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponseDTO {
    private UUID id;
    private String title;
    private double price;
    private String description;
    private String categoryName;
    private double rating;
}
