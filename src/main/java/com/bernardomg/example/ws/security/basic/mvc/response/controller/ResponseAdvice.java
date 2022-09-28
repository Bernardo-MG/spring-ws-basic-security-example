
package com.bernardomg.example.ws.security.basic.mvc.response.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.bernardomg.example.ws.security.basic.mvc.response.model.ErrorResponse;
import com.bernardomg.example.ws.security.basic.mvc.response.model.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * Advice to wrap all the responses into the response object.
 * <p>
 * Unless the response is already an instance of {@link Response}, or the Spring {@link ResponseEntity}, it will be
 * wrapped into a {@code Response}. Paginated data will be wrapped into a {@link PaginatedResponse}.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@ControllerAdvice("com.bernardomg.example.ws")
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * Default constructor.
     */
    public ResponseAdvice() {
        super();
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
            final MediaType selectedContentType, final Class<? extends HttpMessageConverter<?>> selectedConverterType,
            final ServerHttpRequest request, final ServerHttpResponse response) {
        final Object result;

        log.trace("Received {} as response body", body);
        if (body instanceof ResponseEntity<?>) {
            // Avoid wrapping Spring responses
            result = body;
        } else if (body instanceof Response) {
            // Avoid wrapping responses
            result = body;
        } else if (body instanceof ErrorResponse) {
            // Avoid wrapping error responses
            result = body;
        } else if (body == null) {
            log.debug("Received null as response body");
            result = Response.empty();
        } else {
            result = Response.of(body);
        }

        return result;
    }

    @Override
    public boolean supports(final MethodParameter returnType,
            final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

}
