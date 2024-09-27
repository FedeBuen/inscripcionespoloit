package com.poloit.grupo12.inscripciones.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleRecursoNoEncontradoException(
            RecursoNoEncontradoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RolNoValidoException.class)
    public ResponseEntity<ErrorResponse> handleRolNoValidoException(
            RolNoValidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UsuarioIdNoValidoException.class)
    public ResponseEntity<ErrorResponse> handleUsuarioIdNoValidoException(
            UsuarioIdNoValidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailNoValidoException.class)
    public ResponseEntity<ErrorResponse> handleEmailNoValidoException(
            EmailNoValidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailRepetidoException.class)
    public ResponseEntity<ErrorResponse> handleEmailRepetidoException(
            EmailRepetidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(FechaNoValidaException.class)
    public ResponseEntity<ErrorResponse> handleFechaNoValidaException(
            FechaNoValidaException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IdNoValidoException.class)
    public ResponseEntity<ErrorResponse> handleIdNoValidoExceptionn(
            IdNoValidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PasswordNoValidoException.class)
    public ResponseEntity<ErrorResponse> handlePasswordNoValidoException(
            PasswordNoValidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NombreNoValidoException.class)
    public ResponseEntity<ErrorResponse> handleNombreNoValidoException(
            NombreNoValidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ApellidoNoValidoException.class)
    public ResponseEntity<ErrorResponse> handleApellidoNoValidoException(
            ApellidoNoValidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PasswordIncorrectoException.class)
    public ResponseEntity<ErrorResponse> handlePasswordIncorrectoException(
            PasswordIncorrectoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
