package dev.sameer.ecommerceproductservice.Service;

import dev.sameer.ecommerceproductservice.Client.UserServiceClient;
import dev.sameer.ecommerceproductservice.DTO.ProductRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.ProductResponseDTO;
import dev.sameer.ecommerceproductservice.Entity.Category;
import dev.sameer.ecommerceproductservice.Entity.Product;
import dev.sameer.ecommerceproductservice.Exceptions.ProductNotFoundException;
import dev.sameer.ecommerceproductservice.Mapper.EntityToDTOMapper;
import dev.sameer.ecommerceproductservice.Repository.CategoryRepository;
import dev.sameer.ecommerceproductservice.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserServiceClient userServiceClient;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserServiceClient userServiceClient) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userServiceClient = userServiceClient;
    }

    public ProductResponseDTO addProduct(String token,ProductRequestDTO productRequestDTO) {
        if(userServiceClient.validate(token)) {
            Product product = new Product();
            product.setTitle(productRequestDTO.getTitle());
            product.setPrice(productRequestDTO.getPrice());
            product.setDescription(productRequestDTO.getDescription());
            product.setRating(productRequestDTO.getRating());
            Category category = categoryRepository.findCategoryByCategoryName(productRequestDTO.getCategoryName());
            if (category == null) {
                category = new Category();
                category.setCategoryName(productRequestDTO.getCategoryName());
                categoryRepository.save(category);
            }
            product.setCategory(category);
            productRepository.save(product);
            if (category.getProductList() == null)
                category.setProductList(new ArrayList<>());
            category.getProductList().add(product);
            categoryRepository.save(category);
            return EntityToDTOMapper.convertProductEntityToProductDTO(product);
        }
        return null;
    }

    @Override
    public ProductResponseDTO getProduct(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow( () -> new ProductNotFoundException("Product with the id " + productId + " not found"));
        return EntityToDTOMapper.convertProductEntityToProductDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProduct() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        for(Product product : productList)
            productResponseDTOList.add(EntityToDTOMapper.convertProductEntityToProductDTO(product));
        return productResponseDTOList;
    }

    public ProductResponseDTO getProductByTitle(String productTitle) {
        Product product = productRepository.findProductByTitle(productTitle);
        return EntityToDTOMapper.convertProductEntityToProductDTO(product);
    }

    public List<ProductResponseDTO> getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> productList = productRepository.findProductByPriceBetween(minPrice, maxPrice);
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        for(Product product : productList) {
            productResponseDTOList.add(EntityToDTOMapper.convertProductEntityToProductDTO(product));
        }
        return productResponseDTOList;
    }

    @Override
    public ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO updateProductRequestDTO) {
        Product product = productRepository.findById(productId).orElseThrow( () -> new ProductNotFoundException("Product with the id " + productId + " not found"));
        product.setTitle(updateProductRequestDTO.getTitle());
        product.setPrice(updateProductRequestDTO.getPrice());
        product.setDescription(updateProductRequestDTO.getDescription());
        product.setRating(updateProductRequestDTO.getRating());
        Category category = categoryRepository.findCategoryByCategoryName(updateProductRequestDTO.getCategoryName());
        //It means the that category is not present in the db, so create a new category
        if(category == null) {
            //Create a category
            category = new Category();
            category.setCategoryName(updateProductRequestDTO.getCategoryName());
            categoryRepository.save(category);
        }
        product.setCategory(category);
        productRepository.save(product);
        if(category.getProductList() == null) {
            category.setProductList(new ArrayList<>());
        }
        category.getProductList().add(product);
        categoryRepository.save(category);
        return EntityToDTOMapper.convertProductEntityToProductDTO(product);
    }

    @Override
    public ProductResponseDTO deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("This product does not exist already"));
        productRepository.delete(product);
        return EntityToDTOMapper.convertProductEntityToProductDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getProductLTH() {
        List<Product> productList = productRepository.findByOrderByPriceAsc();
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        for(Product product : productList)
            productResponseDTOList.add(EntityToDTOMapper.convertProductEntityToProductDTO(product));
        return productResponseDTOList;

    }

    @Override
    public List<ProductResponseDTO> getProductHTL() {
        List<Product> productList = productRepository.findByOrderByPriceDesc();
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        for(Product product : productList)
            productResponseDTOList.add(EntityToDTOMapper.convertProductEntityToProductDTO(product));
        return productResponseDTOList;
    }
}
