package dev.sameer.ecommerceproductservice.Controller;

import dev.sameer.ecommerceproductservice.DTO.ProductRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.ProductResponseDTO;
import dev.sameer.ecommerceproductservice.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    //Creating the product
    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.addProduct(productRequestDTO));
    }

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

    //Update the product
    @PutMapping("updateProduct/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID productId, @RequestBody ProductRequestDTO productRequestDTO) {
        return ResponseEntity.ok(productService.updateProduct(productId, productRequestDTO));
    }

    //Deleting the product
    @DeleteMapping("deleteProduct/{productId}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }
}

