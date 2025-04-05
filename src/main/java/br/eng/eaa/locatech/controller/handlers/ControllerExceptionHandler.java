package br.eng.eaa.locatech.controller.handlers;

import br.eng.eaa.locatech.dto.ResourceNotFoundDTO;
import br.eng.eaa.locatech.dto.ValidationErrorDTO;
import br.eng.eaa.locatech.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handleResourceNotFoundException(ResourceNotFoundException ex) {
        var status = HttpStatus.NOT_FOUND;
        var resourceNotFoundDTO = new ResourceNotFoundDTO(ex.getMessage(), status.value());
        return ResponseEntity.status(status).body(resourceNotFoundDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handleResourceNotFoundException(MethodArgumentNotValidException ex) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<String>();
        ex.getFieldErrors().forEach(error -> {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        });
        var resourceNotFoundDTO = new ResourceNotFoundDTO(ex.getMessage(), status.value());
        return ResponseEntity.status(status).body(new ValidationErrorDTO(errors, status.value()));
    }

}
