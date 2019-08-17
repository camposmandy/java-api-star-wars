package br.com.amandacampos.starwarsplanets.resources.exceptions;

import br.com.amandacampos.starwarsplanets.models.Error;
import br.com.amandacampos.starwarsplanets.services.exception.PlanetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PlanetExceptionHandler {

    /**
     * Resource not found exception.
     * @param e PlanetNotFoundException
     * @param request HttpServletRequest
     * @return Error (custom)
     */
    @ExceptionHandler(PlanetNotFoundException.class)
    public ResponseEntity<Error> objectNotFound(PlanetNotFoundException e, HttpServletRequest request) {
        Error error = new Error(HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURL().toString(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
