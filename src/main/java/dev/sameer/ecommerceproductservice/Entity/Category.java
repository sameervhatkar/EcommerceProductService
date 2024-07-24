package dev.sameer.ecommerceproductservice.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String categoryName;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> productList;
}
