package com.capgemini.amazingbank.exception;

import org.springframework.http.ResponseEntity;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
