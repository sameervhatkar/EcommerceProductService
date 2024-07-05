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
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequestDTO));
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/getCategoryById/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryByID(categoryId));
    }

    @GetMapping("/updateCategory/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable UUID categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequestDTO));
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }

    @GetMapping("/sumOfAllProductsUnderCategory/{categoryId}")
    public ResponseEntity<Double> sumOfAllProductsUnderCategory(@PathVariable UUID categoryId) {
        return ResponseEntity.ok(categoryService.sumOfAllProductsUnderCategory(categoryId));
    }


}
