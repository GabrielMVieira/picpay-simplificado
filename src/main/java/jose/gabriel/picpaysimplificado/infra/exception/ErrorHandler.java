package jose.gabriel.picpaysimplificado.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// tratar mais erros
// trocar exceptions
// subir para o git
// adicionar ao curriculo
// revisar curriculo gupy

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treat404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDTO> threatBadRequest(BadRequestException exception){
        ExceptionDTO response = new ExceptionDTO(exception.getMessage(), 400);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> threatInternalServerError(){
        ExceptionDTO response = new ExceptionDTO("Internal Server Error", 500);
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDTO> handleCustomException(CustomException ex) {
        ExceptionDTO response = new ExceptionDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
