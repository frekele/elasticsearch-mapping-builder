package org.frekele.elasticsearch.exceptions;

import java.io.Serializable;

public class InvalidDocumentClassException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public InvalidDocumentClassException(String message) {
        super(message);
    }

    public InvalidDocumentClassException(Throwable cause) {
        super(cause);
    }

    public InvalidDocumentClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDocumentClassException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
