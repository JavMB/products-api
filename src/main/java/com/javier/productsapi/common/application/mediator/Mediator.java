package com.javier.productsapi.common.application.mediator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Mediator para despachar peticiones a sus respectivos handlers.
 * <p>
 * Utiliza un mapa donde la clave es la clase de la petición y el valor es el handler correspondiente.
 * Permite desacoplar el envío de peticiones de su manejo concreto.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *   R resultado = mediator.dispatch(new ProductCreateRequest(...));
 * </pre>
 * <p>
 * Tipos genéricos:
 * <ul>
 *   <li><b>R</b>: tipo del resultado que devuelve el handler (por ejemplo, ProductDto, Boolean, etc).</li>
 *   <li><b>T</b>: tipo de la petición que extiende de Request&lt;R&gt; (por ejemplo, ProductCreateRequest).</li>
 * </ul>
 * Así, el método dispatch puede manejar cualquier tipo de petición y resultado, manteniendo el código flexible y seguro.
 */
@Component
@Slf4j
public class Mediator {

    private final Map<Class<?>, RequestHandler<?, ?>> requestHandlerMap; // interesante el uso de la clase como clave en vez de Strings


    // cualquer tipo de resquestHandler
    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        this.requestHandlerMap = requestHandlers.stream()
                .collect(Collectors.toMap(RequestHandler::getRequestType, Function.identity()));
    }

    public <R, T extends Request<R>> R dispatch(T request) {
        RequestHandler<T, R> handler = (RequestHandler<T, R>) requestHandlerMap.get(request.getClass());
        if (handler == null) {
            log.error("No handler found for request {}", request);
            throw new RuntimeException("No handler found for request type " + request.getClass());
        }
        return handler.handle(request);


    }

    @Async
    public <R, T extends Request<R>> void dispatchAsync(T request) {
        dispatch(request);

    }
}
