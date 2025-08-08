package com.javier.productsapi.common.application.mediator;

/**
 * Interfaz base para definir una petición en el patrón Mediator.
 * <p>
 * Tipo genérico <b>T</b>: representa el tipo de respuesta que se espera al procesar la petición.
 * Por ejemplo, si la petición crea un producto, T podría ser ProductDto.
 */
public interface Request<T> {
}
