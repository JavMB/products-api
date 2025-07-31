package com.javier.productsapi.common.mediator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mediator {

    private final Map<Class<?>, RequestHandler<?, ?>> handlersMap;


    // cualquer tipo de resquestHandler
    public Mediator(List<RequestHandler<?, ?>> requestHandlers) {
        this.handlersMap = requestHandlers.stream()
                .collect(Collectors.toMap(RequestHandler::getRequestType, Function.identity()));
    }

    public void dispatch(String message) {

    }

}
