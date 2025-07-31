package com.javier.productsapi.common.mediator;
// clase T type de entrada clase R respuesta que devolvemos
public interface RequestHandler<T extends Request<T>, R> {

    R handle(T request);

    Class<T> getRequestType();


}
