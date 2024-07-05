package dev.sameer.ecommerceproductservice.Exceptions;

import dev.sameer.ecommerceproductservice.DTO.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handlesProductNotFoundException(ProductNotFoundException productNotFoundException) {
        ExceptionResponseDTO productExceptionResponseDTO = new ExceptionResponseDTO(
                productNotFoundException.getMessage(),
                404
        );
        return new ResponseEntity<>(productExceptionResponseDTO, HttpStatus.NOT_FOUND);
    }
}
