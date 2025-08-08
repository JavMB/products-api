package com.javier.productsapi.common.application.mediator;

/**
 * Interfaz para definir un handler en el patrón Mediator.
 * <p>
 * Tipos genéricos:
 * <ul>
 *   <li><b>T</b>: tipo de la petición, debe extender de Request&lt;R&gt;.</li>
 *   <li><b>R</b>: tipo de la respuesta que devuelve el handler.</li>
 * </ul>
 * El método handle procesa la petición y devuelve la respuesta.
 * El método getRequestType retorna la clase de la petición que maneja este handler.
 */
public interface RequestHandler<T extends Request<R>, R> {

    R handle(T request);

    Class<T> getRequestType();


}
