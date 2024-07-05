package dev.sameer.ecommerceproductservice.Exceptions;

import dev.sameer.ecommerceproductservice.DTO.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CateogoryExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handlesCategoryNotFoundException(CategoryNotFoundException categoryNotFoundException) {
        ExceptionResponseDTO categoryExceptionResponseDTO = new ExceptionResponseDTO(
                categoryNotFoundException.getMessage(),
                404
        );
        return new ResponseEntity<>(categoryExceptionResponseDTO, HttpStatus.NOT_FOUND);
    }
}
