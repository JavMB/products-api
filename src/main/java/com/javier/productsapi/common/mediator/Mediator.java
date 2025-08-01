package com.javier.productsapi.common.mediator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
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
            throw new RuntimeException("No handler found for request type " + request.getClass());
        }
        return handler.handle(request);


    }

}
