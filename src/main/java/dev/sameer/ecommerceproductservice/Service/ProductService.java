package dev.sameer.ecommerceproductservice.Service;

import dev.sameer.ecommerceproductservice.DTO.ProductRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.ProductResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponseDTO addProduct(String jwtToken, ProductRequestDTO productRequestDTO);
    ProductResponseDTO updateProduct(String token, UUID productId, ProductRequestDTO productRequestDTO);
    ProductResponseDTO deleteProduct(String token, UUID productId);

    ProductResponseDTO getProduct(UUID id);
    List<ProductResponseDTO> getAllProduct();
    List<ProductResponseDTO> getProductLTH();
    List<ProductResponseDTO> getProductHTL();
}
