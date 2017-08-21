package org.frekele.elasticsearch.mapping.exceptions;

import java.io.Serializable;

/**
 * @author frekele - Leandro Kersting de Freitas
 */
public class MappingBuilderException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public MappingBuilderException(String message) {
        super(message);
    }

    public MappingBuilderException(Throwable cause) {
        super(cause);
    }

    public MappingBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappingBuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
