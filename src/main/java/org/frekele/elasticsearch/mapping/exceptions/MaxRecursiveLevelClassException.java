package org.frekele.elasticsearch.mapping.exceptions;

import java.io.Serializable;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class MaxRecursiveLevelClassException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public MaxRecursiveLevelClassException(String message) {
        super(message);
    }

    public MaxRecursiveLevelClassException(Throwable cause) {
        super(cause);
    }

    public MaxRecursiveLevelClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaxRecursiveLevelClassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
