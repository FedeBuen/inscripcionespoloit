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

    @ExceptionHandler(RolNoAutorizadoException.class)
    public ResponseEntity<ErrorResponse> handleRolNoAutorizadoException(
            RolNoAutorizadoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EstadoNoValidoException.class)
    public ResponseEntity<ErrorResponse> handleEstadoNoValidoException(
            EstadoNoValidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RecursoExistenteException.class)
    public ResponseEntity<ErrorResponse> handleRecursoExistenteException(
            RecursoExistenteException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenNoValidoException.class)
    public ResponseEntity<ErrorResponse> handleTokenNoValidoException(
            TokenNoValidoException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(TokenExistenteException.class)
    public ResponseEntity<ErrorResponse> handleTokenExistenteException(
            TokenExistenteException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ClaveForaneaException.class)
    public ResponseEntity<ErrorResponse> handleClaveForaneaException(
            ClaveForaneaException ex,
            HttpServletRequest request) {
        String url = request.getRequestURI();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), url);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
