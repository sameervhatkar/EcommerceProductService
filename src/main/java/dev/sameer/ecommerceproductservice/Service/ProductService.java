package dev.sameer.ecommerceproductservice.Service;

import dev.sameer.ecommerceproductservice.DTO.ProductRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.ProductResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO getProduct(UUID id);
    List<ProductResponseDTO> getAllProduct();
    ProductResponseDTO updateProduct(UUID productId, ProductRequestDTO productRequestDTO);
    ProductResponseDTO deleteProduct(UUID productId);
}
