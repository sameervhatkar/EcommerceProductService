package dev.sameer.ecommerceproductservice.Controller;

import dev.sameer.ecommerceproductservice.DTO.CategoryRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.CategoryResponseDTO;
import dev.sameer.ecommerceproductservice.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestHeader("Authorization") String token, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.ok(categoryService.createCategory(token, categoryRequestDTO));
    }

    @GetMapping("/updateCategory/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@RequestHeader("Authorization") String token, @PathVariable UUID categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(token, categoryId, categoryRequestDTO));
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<Boolean> deleteCategory(@RequestHeader("Authorization") String token, @PathVariable UUID categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(token, categoryId));
    }

    @GetMapping("/sumOfAllProductsUnderCategory/{categoryId}")
    public ResponseEntity<Double> sumOfAllProductsUnderCategory(@RequestHeader("Authorization") String token, @PathVariable UUID categoryId) {
        return ResponseEntity.ok(categoryService.sumOfAllProductsUnderCategory(token, categoryId));
    }



    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryByID(categoryId));
    }

    @GetMapping("/getProductInAscUnderCategory/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getProductAscCategory(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(categoryService.getProductAscCategory(categoryId));
    }

    @GetMapping("/getProductInDesUnderCategory/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getProductDesCategory(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(categoryService.getProductDesCategory(categoryId));
    }

}