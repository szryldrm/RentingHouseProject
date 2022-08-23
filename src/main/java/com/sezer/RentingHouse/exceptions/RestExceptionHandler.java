package com.sezer.RentingHouse.exceptions;

import com.sezer.RentingHouse.exceptions.models.ErrorModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Returning Validation Exception
   *
   * @param ex the exception
   * @param headers the headers to be written to the response
   * @param status the selected response status
   * @param request the current request
   * @return
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    var error =
        new ErrorModel(
            HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().toString());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  /**
   * Returning Entity Not Found Exception.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(EntityNotFoundException.class)
  private ResponseEntity<ErrorModel> handleEntityNotFound(EntityNotFoundException ex) {
    var error = new ErrorModel(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
}
