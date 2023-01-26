package com.proyecto.desafioTienda.exception;

import com.proyecto.desafioTienda.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    private ErrorResponseDTO errorResponseDTO;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> resourceNotFoundException(Exception e){
        errorResponseDTO= new ErrorResponseDTO();
        errorResponseDTO.setStatusCode(404);
        errorResponseDTO.setStatus("ERROR");
        errorResponseDTO.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDTO> resourceAlreadyExistException(Exception e){
        errorResponseDTO= new ErrorResponseDTO();
        errorResponseDTO.setStatusCode(409);
        errorResponseDTO.setStatus("ERROR");
        errorResponseDTO.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> illegalArgumentException(Exception e){
        errorResponseDTO= new ErrorResponseDTO();
        errorResponseDTO.setStatusCode(400);
        errorResponseDTO.setStatus("ERROR");
        errorResponseDTO.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
