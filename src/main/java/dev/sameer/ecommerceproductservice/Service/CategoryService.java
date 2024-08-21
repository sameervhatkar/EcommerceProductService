package dev.sameer.ecommerceproductservice.Service;

import dev.sameer.ecommerceproductservice.DTO.CategoryRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.CategoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDTO createCategory(String token, CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO updateCategory(String token, UUID categoryId, CategoryRequestDTO categoryRequestDTO);
    Boolean deleteCategory(String token, UUID categoryId);
    double sumOfAllProductsUnderCategory(String token, UUID categoryId);


    List<CategoryResponseDTO> getAllCategory();
    CategoryResponseDTO getCategoryByID(UUID categoryId);
    CategoryResponseDTO getProductAscCategory(UUID categoryId);
    CategoryResponseDTO getProductDesCategory(UUID categoryId);
}
