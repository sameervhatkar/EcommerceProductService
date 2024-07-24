package dev.sameer.ecommerceproductservice.Service;

import dev.sameer.ecommerceproductservice.DTO.CategoryRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.CategoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    List<CategoryResponseDTO> getAllCategory();
    CategoryResponseDTO getCategoryByID(UUID categoryId);
    CategoryResponseDTO updateCategory(UUID categoryId, CategoryRequestDTO categoryRequestDTO);
    Boolean deleteCategory(UUID categoryId);
    double sumOfAllProductsUnderCategory(UUID categoryId);
    CategoryResponseDTO getProductAscCategory(UUID categoryId);

    CategoryResponseDTO getProductDesCategory(UUID categoryId);
}
