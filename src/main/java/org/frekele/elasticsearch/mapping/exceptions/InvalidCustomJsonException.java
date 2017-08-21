package org.frekele.elasticsearch.mapping.exceptions;

import java.io.Serializable;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class InvalidCustomJsonException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public InvalidCustomJsonException(String message) {
        super(message);
    }

    public InvalidCustomJsonException(Throwable cause) {
        super(cause);
    }

    public InvalidCustomJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCustomJsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
