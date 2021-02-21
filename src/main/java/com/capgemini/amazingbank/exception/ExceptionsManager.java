package com.capgemini.amazingbank.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author junidryves@yahoo.fr
 * @version 0.1
 * @since 02-19-2021
 */
@Service
@Slf4j
public class ExceptionsManager {
    public String buildExceptionMsg(String message, String relatedResource, String relatedValues) {
        message = isNotEmpty(relatedResource) ? message.concat(" Related resource: ").concat(relatedResource) : "";
        message = isNotEmpty(relatedValues) ? message.concat(". Related values: ").concat(relatedValues) : "";
        return message;
    }

    public String logAndThrowHttpClientErrorException(String message, String relatedResource, String relatedValues) {
        String excpMessage = buildExceptionMsg(message, relatedResource, relatedValues);
        log.error(excpMessage);
        throw new HttpClientErrorException(BAD_REQUEST, excpMessage);
    }
}
