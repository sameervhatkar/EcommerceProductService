# E-commerce Product Microservice

# Overview

This microservice is responsible for managing products and categories in an e-commerce platform. It provides endpoints for creating, reading, updating, and deleting products and categories. Additionally, it includes functionalities for sorting products based on price and communicating with the User microservice to validate user sessions.

## Technologies Used
* Java
* Spring Boot
* JPA (Java Persistence API)
* Hibernate
* MySQL
* RESTful API

## Architecture
The microservice follows a layered architecture with the following packages:
* Client: Handles communication with other microservices.
* Controller: Contains REST controllers for handling HTTP requests.
* DTO (Data Transfer Objects): Defines the objects used to transfer data between layers.
* Entity: Represents the database entities.
* Exceptions: Custom exception handling.
* Mapper: Maps entities to DTOs and vice versa.
* Repository: JPA repositories for database access.
* Service: Contains the business logic.

## Entities
### Product
```Java
public class Product {
    private String title;
    private double price;
    private String description;
    private Category category;
    private double rating;
}
```

### Category
``` Java
public class Category {
    private String categoryName;
    private List<Product> productList;
}
```

## Endpoints

### CategoryController
* Create Category: POST /createCategory
  * Request Body: CategoryRequestDTO
  - Header: Authorization token
  - Response: CategoryResponseDTO
* Get All Categories: GET /getAllCategory
  -	Response: List<CategoryResponseDTO>
  - Get Category by ID: GET /getCategoryById/{categoryId}
  - Response: CategoryResponseDTO
* Update Category: GET /updateCategory/{categoryId}
  -	Request Body: CategoryRequestDTO
  -	Header: Authorization token
  -	Response: CategoryResponseDTO
* Delete Category: DELETE /deleteCategory/{categoryId}
  -	Header: Authorization token
  -	Response: Boolean
* Sum of All Products Under Category: GET /sumOfAllProductsUnderCategory/{categoryId}
  -	Header: Authorization token
  -	Response: Double
* Get Products in Ascending Order by Price: GET /getProductInAscUnderCategory/{categoryId}
  *	Response: CategoryResponseDTO
* Get Products in Descending Order by Price: GET /getProductInDesUnderCategory/{categoryId}
  *	Response: CategoryResponseDTO

### ProductController
* Add Product: POST /addProduct
  - Request Body: ProductRequestDTO
  - Header: Authorization token
  - Response: ProductResponseDTO
* Get Product by ID: GET /product/{productId}
  - Response: ProductResponseDTO
* Get Product by Title: GET /product/name={productTitle}
  - Response: ProductResponseDTO
* Get Products in Price Range: GET /productsInRange/lowPrice={minPrice}-highPrice={maxPrice}
  - Response: List<ProductResponseDTO>
* Get All Products: GET /getAllProducts
  - Response: List<ProductResponseDTO>
* Update Product: PUT /updateProduct/{productId}
  - Request Body: ProductRequestDTO
  - Header: Authorization token
  - Response: ProductResponseDTO
* Delete Product: DELETE /deleteProduct/{productId}
  - Header: Authorization token
  - Response: ProductResponseDTO
* Get Products Low to High by Price: GET /getProductLowToHigh
  - Response: List<ProductResponseDTO>
* Get Products High to Low by Price: GET /getProductHighToLow
  - Response: List<ProductResponseDTO>

## Services

### CategoryServiceImpl
* createCategory: Creates a new category.
* getAllCategory: Retrieves all categories.
* getCategoryByID: Retrieves a category by its ID.
* updateCategory: Updates an existing category.
* deleteCategory: Deletes a category.
* sumOfAllProductsUnderCategory: Calculates the sum of prices of all products under a category.
* getProductAscCategory: Retrieves products under a category sorted by price in ascending order.
* getProductDesCategory: Retrieves products under a category sorted by price in descending order.

### ProductServiceImpl
* addProduct: Adds a new product.
* getProduct: Retrieves a product by its ID.
* getAllProduct: Retrieves all products.
* getProductByTitle: Retrieves a product by its title.
* getProductsByPriceRange: Retrieves products within a specified price range.
* updateProduct: Updates an existing product.
* deleteProduct: Deletes a product.
* getProductLTH: Retrieves products sorted by price from low to high.
* getProductHTL: Retrieves products sorted by price from high to low.
