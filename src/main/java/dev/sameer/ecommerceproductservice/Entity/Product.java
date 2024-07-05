package dev.sameer.ecommerceproductservice.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private double price;
    private String description;
    @ManyToOne
    private Category category;
    private double rating;
}

/*
    Product     Category
    1           1
     M           1
 */
