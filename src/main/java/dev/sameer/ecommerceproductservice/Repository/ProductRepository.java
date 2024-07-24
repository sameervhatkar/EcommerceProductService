package dev.sameer.ecommerceproductservice.Repository;

import dev.sameer.ecommerceproductservice.Entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findProductByTitle(String productTitle);
    List<Product> findProductByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByOrderByPriceAsc();
    List<Product> findByOrderByPriceDesc();

}
