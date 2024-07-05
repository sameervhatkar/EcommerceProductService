package dev.sameer.ecommerceproductservice.Service;

import dev.sameer.ecommerceproductservice.DTO.CategoryRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.CategoryResponseDTO;
import dev.sameer.ecommerceproductservice.Entity.Category;
import dev.sameer.ecommerceproductservice.Entity.Product;
import dev.sameer.ecommerceproductservice.Exceptions.CategoryNotFoundException;
import dev.sameer.ecommerceproductservice.Mapper.EntityToDTOMapper;
import dev.sameer.ecommerceproductservice.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setCategoryName(categoryRequestDTO.getCategoryName());
        categoryRepository.save(category);
        return EntityToDTOMapper.convertCategoryEntitytoCategoryDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        for(Category category : categories)
            categoryResponseDTOS.add(EntityToDTOMapper.convertCategoryEntitytoCategoryDTO(category));
        return categoryResponseDTOS;
    }

    @Override
    public CategoryResponseDTO getCategoryByID(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category Not Found")
        );
        return EntityToDTOMapper.convertCategoryEntitytoCategoryDTO(category);

    }

    @Override
    public CategoryResponseDTO updateCategory(UUID categoryId, CategoryRequestDTO categoryRequestDTO) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
        category.setCategoryName(categoryRequestDTO.getCategoryName());
        categoryRepository.save(category);
        return EntityToDTOMapper.convertCategoryEntitytoCategoryDTO(category);

    }

    @Override
    public CategoryResponseDTO deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category Not found")
        );
        categoryRepository.delete(category);
        return EntityToDTOMapper.convertCategoryEntitytoCategoryDTO(category);
    }

    public double sumOfAllProductsUnderCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category Not Found")
        );
        List<Product> productList = category.getProductList();
        double sum = 0;
        if(!productList.isEmpty()) {
            for(Product product : productList) {
                sum += product.getPrice();
            }
        }
        return sum;
    }
}
