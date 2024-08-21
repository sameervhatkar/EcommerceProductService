package dev.sameer.ecommerceproductservice.Exceptions;

import dev.sameer.ecommerceproductservice.DTO.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TokenExceptionHandler {
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionResponseDTO> handlesTokenExceptions(InvalidTokenException invalidTokenException) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                invalidTokenException.getMessage(),
                401
        );
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.UNAUTHORIZED);
    }
}
