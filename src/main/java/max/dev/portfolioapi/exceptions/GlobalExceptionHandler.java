package max.dev.portfolioapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<?> handleException(GlobalException exp) {
        return ResponseEntity
                .badRequest()
                .body(exp.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            Map.Entry<String, String> entry = convertErrorToMapEntry(error);
            errors.put(entry.getKey(), entry.getValue());
        }

        return errors;
    }

    private Map.Entry<String, String> convertErrorToMapEntry(ObjectError error) {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        return new AbstractMap.SimpleEntry<>(fieldName, errorMessage);
    }
}
