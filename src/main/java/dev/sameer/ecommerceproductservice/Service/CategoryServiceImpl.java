package dev.sameer.ecommerceproductservice.Service;

import dev.sameer.ecommerceproductservice.Client.UserServiceClient;
import dev.sameer.ecommerceproductservice.DTO.CategoryRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.CategoryResponseDTO;
import dev.sameer.ecommerceproductservice.Entity.Category;
import dev.sameer.ecommerceproductservice.Entity.Product;
import dev.sameer.ecommerceproductservice.Exceptions.CategoryNotFoundException;
import dev.sameer.ecommerceproductservice.Exceptions.InvalidTokenException;
import dev.sameer.ecommerceproductservice.Mapper.EntityToDTOMapper;
import dev.sameer.ecommerceproductservice.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserServiceClient userServiceClient;

    @Override
    public CategoryResponseDTO createCategory(String token, CategoryRequestDTO categoryRequestDTO) {
        if(userServiceClient.validate(token)) {
            Category category = new Category();
            category.setCategoryName(categoryRequestDTO.getCategoryName());
            categoryRepository.save(category);
            return EntityToDTOMapper.convertCategoryEntitytoCategoryDTO(category);
        }
        else
            throw new InvalidTokenException("Invalid token, Token might be expired");
    }

    @Override
    public CategoryResponseDTO updateCategory(String token, UUID categoryId, CategoryRequestDTO categoryRequestDTO) {
        if(userServiceClient.validate(token)) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
            category.setCategoryName(categoryRequestDTO.getCategoryName());
            categoryRepository.save(category);
            return EntityToDTOMapper.convertCategoryEntitytoCategoryDTO(category);
        }
        else
            throw new InvalidTokenException("Invalid token, Token might be expired");

    }

    @Override
    public Boolean deleteCategory(String token, UUID categoryId) {
        if(userServiceClient.validate(token)) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(
                    () -> new CategoryNotFoundException("Category Not found")
            );
            categoryRepository.delete(category);
            return true;
        }
        else
            throw new InvalidTokenException("Invalid token, Token might be expired");
    }

    public double sumOfAllProductsUnderCategory(String token, UUID categoryId) {
        if(userServiceClient.validate(token)) {
            Category category = categoryRepository.findById(categoryId).orElseThrow(
                    () -> new CategoryNotFoundException("Category Not Found")
            );
            List<Product> productList = category.getProductList();
            double sum = 0;
            if (!productList.isEmpty()) {
                for (Product product : productList) {
                    sum += product.getPrice();
                }
            }
            return sum;
        }
        else
            throw new InvalidTokenException("Invalid token, Token might be expired");
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
    public CategoryResponseDTO getProductAscCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category Not Found")
        );
        List<Product> productList = category.getProductList();
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        category.setProductList(productList);
        return EntityToDTOMapper.convertCategoryEntitytoCategoryDTO(category);
    }

    @Override
    public CategoryResponseDTO getProductDesCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category Not Found")
        );
        List<Product> productList = category.getProductList();
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });
        category.setProductList(productList);
        return EntityToDTOMapper.convertCategoryEntitytoCategoryDTO(category);
    }
}
