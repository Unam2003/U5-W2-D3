package emanuelepiemonte.U5_W2_D1.exceptions;

import emanuelepiemonte.U5_W2_D1.payloads.ErrorsDTO;
import emanuelepiemonte.U5_W2_D1.payloads.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {
//    @ExceptionHandler(BadRequestException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
//    public ErrorsDTO handleBadRequest(BadRequestException ex) {
//        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
//    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handleValidationErrors(ValidationException ex) {
        return new ErrorsWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrors());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    // Tra le parentesi specifico il tipo di eccezione che dovrà gestire questo metodo
    public ErrorsDTO handleNotFoundEx(NotFoundException ex) { // Abbiamo accesso anche alla stessa eccezione
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }
//
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
//    public ErrorsDTO handleGenericEx(Exception ex) {
//        // Nel caso di tutti gli altri errori, mandiamo un 500 (non svelando però i dettagli di tale problema)
//        ex.printStackTrace(); // E' però importante non nascondere neanche a noi stessi la causa dell'errore
//        // quindi un qualcosa tipo un stack trace ci può aiutare nella risoluzione del bug
//        return new ErrorsDTO("C'è stato un errore lato server, giuro che lo risolveremo presto!", LocalDateTime.now());
//    }
}
