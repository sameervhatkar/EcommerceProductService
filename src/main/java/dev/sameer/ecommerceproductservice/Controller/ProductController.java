package dev.sameer.ecommerceproductservice.Controller;

import dev.sameer.ecommerceproductservice.DTO.LoginRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.ProductRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.ProductResponseDTO;
import dev.sameer.ecommerceproductservice.DTO.UserResponseDTO;
import dev.sameer.ecommerceproductservice.Service.ProductServiceImpl;
import dev.sameer.ecommerceproductservice.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private UserService userService;

    //Endpoints that need authentication
    //Creating the product
    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestHeader("Authorization") String token, @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.addProduct(token, productRequestDTO));
    }

    //Update the product
    @PutMapping("updateProduct/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@RequestHeader("Authorization") String token, @PathVariable UUID productId, @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.updateProduct(token, productId, productRequestDTO));

    }

    //Deleting the product
    @DeleteMapping("deleteProduct/{productId}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@RequestHeader("Authorization") String token, @PathVariable UUID productId) {
        return ResponseEntity.ok(productService.deleteProduct(token, productId));
    }

    //Endpoints that doesn't need any authentication, user are just seeing the products
    //Reading the product or products
    @GetMapping("product/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping("product/name={productTitle}")
    public ResponseEntity<ProductResponseDTO> getProductByTitle(@PathVariable String productTitle) {
        return ResponseEntity.ok(productService.getProductByTitle(productTitle));
    }

    @GetMapping("/productsInRange/lowPrice={minPrice}-highPrice={maxPrice}")
    public ResponseEntity<List<ProductResponseDTO>> getproductsInPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(minPrice, maxPrice));
    }

    @GetMapping("getAllProducts")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("getProductLowToHigh")
    public ResponseEntity<List<ProductResponseDTO>> getProductsLTH() {
        return ResponseEntity.ok(productService.getProductLTH());
    }

    @GetMapping("getProductHighToLow")
    public ResponseEntity<List<ProductResponseDTO>> getProductsHTL() {
        return ResponseEntity.ok(productService.getProductHTL());
    }
}

