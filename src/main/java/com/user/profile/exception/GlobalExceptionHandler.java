package com.user.profile.exception;

import jakarta.validation.ConstraintViolation;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponseMessage> handleUserAlreadyExistException(UserAlreadyExistException ex)
    {
        return getMessage(ex,HttpStatus.CONFLICT,"User Already Exist !! Registration Failed");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseMessage> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    )
    {
        List<String> errors=ex.getBindingResult().getFieldErrors().stream()
                .map(err->err.unwrap(ConstraintViolation.class))
                .map(err -> String.format("'%s' %s", err.getPropertyPath(), err.getMessage()))
                .toList();
        return getMessage(ex,HttpStatus.UNPROCESSABLE_ENTITY, String.valueOf(errors));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseMessage> handleMethodArgumentNotValidException(
            UserNotFoundException ex
    )
    {
        return getMessage(ex,HttpStatus.BAD_REQUEST,"User Not Found !!");
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseMessage> handleInvalidPasswordException(InvalidPasswordException ex)
    {
        return getMessage(ex,HttpStatus.UNAUTHORIZED,"Invalid Current Password");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseMessage> handleRunException(RuntimeException ex)
    {
        return getMessage(ex,HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
    }

    private ResponseEntity<ErrorResponseMessage> getMessage(Exception ex, HttpStatus status, String message)
    {
        ErrorResponseMessage errorResponseMessage=new ErrorResponseMessage();
        //errorResponseMessage.setError(ex.getMessage());
        errorResponseMessage.setStatus(status.value());
        errorResponseMessage.setMessage(message);
        return new ResponseEntity<>(errorResponseMessage,status);
    }
}
