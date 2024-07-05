package dev.sameer.ecommerceproductservice.Mapper;

import dev.sameer.ecommerceproductservice.DTO.CategoryResponseDTO;
import dev.sameer.ecommerceproductservice.DTO.ProductResponseDTO;
import dev.sameer.ecommerceproductservice.Entity.Product;
import dev.sameer.ecommerceproductservice.Entity.Category;
import java.util.ArrayList;
import java.util.List;


public class EntityToDTOMapper {

    public static ProductResponseDTO convertProductEntityToProductDTO(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setTitle(product.getTitle());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setDescription(product.getDescription());
        productResponseDTO.setCategoryName(product.getCategory().getCategoryName());
        productResponseDTO.setRating(product.getRating());
        return productResponseDTO;
    }

    public static CategoryResponseDTO convertCategoryEntitytoCategoryDTO(Category category) {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryId(category.getId());
        categoryResponseDTO.setCategoryName(category.getCategoryName());
        List<Product> productList =  category.getProductList();
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        if(productList != null) {
            for (Product product : productList) {
                productResponseDTOList.add(convertProductEntityToProductDTO(product));
            }
        }
        categoryResponseDTO.setProductList(productResponseDTOList);
        return categoryResponseDTO;
    }
}
