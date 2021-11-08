package tasks.can.linkconverter.infrastructure.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static tasks.can.linkconverter.infrastructure.error.ServiceError.*;

/**
 * Exception handling for API layer.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        final ErrorModel apiError;
        if (ex instanceof LinkConverterException) {
            LinkConverterException exception = (LinkConverterException) ex;
            apiError = new ErrorModel(exception.getMessage(), exception.getCode());
        } else {
            apiError = new ErrorModel(UNKNOWN_SERVICE_EXCEPTION.getMessage(), UNKNOWN_SERVICE_EXCEPTION.getCode());
        }

        return new ResponseEntity<>(
                apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
