package dev.sameer.ecommerceproductservice.Repository;

import dev.sameer.ecommerceproductservice.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findCategoryByCategoryName(String categoryName);
}
