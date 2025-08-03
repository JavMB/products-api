package com.javier.productsapi.common.exceptions;

import com.javier.productsapi.product.domain.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para la API de productos.
 * <p>
 * Esta clase se encarga de interceptar las excepciones que ocurren en cualquier controlador
 * de la aplicación y transformarlas en respuestas HTTP estructuradas y comprensibles.
 * <p>
 * Al usar @RestControllerAdvice, Spring automáticamente aplica este manejador a todos
 * los controladores REST de la aplicación, proporcionando un punto centralizado para
 * el manejo de errores y asegurando respuestas consistentes.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Maneja las excepciones de validación de datos de entrada.
     * <p>
     * Este metodo se ejecuta automáticamente cuando Spring detecta errores de validación
     * en los DTOs (como campos requeridos faltantes, formatos incorrectos, etc.).
     * Extrae todos los errores de validación y los agrupa en un mapa para proporcionar
     * información detallada sobre qué campos específicos fallaron y por qué.
     * <p>
     * Ejemplo de respuesta:
     * {
     * "message": "Validation failed",
     * "type": "MethodArgumentNotValidException",
     * "path": "/api/products",
     * "errors": {
     * "name": "El nombre no puede estar vacío",
     * "price": "El precio debe ser mayor que 0"
     * }
     * }
     *
     * @param request   La petición HTTP que causó el error, utilizada para obtener la URI
     * @param exception La excepción de validación que contiene todos los errores detectados
     * @return ErrorMessage Objeto estructurado con los detalles del error para el cliente
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, MethodArgumentNotValidException exception) {


        Map<String, String> errors = new HashMap<>();

        // Extraer cada error de validación y asociarlo con su campo correspondiente
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ErrorMessage(exception.getMessage(),
                exception.getClass().getSimpleName(), request.getRequestURI(), errors);

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ErrorMessage notFound(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception.getMessage(),
                exception.getClass().getSimpleName(), request.getRequestURI());

    }


}
