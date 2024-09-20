package org.example.booklibrary.exception;

public class BookNotFoundException extends RuntimeException {

    private final String requestId;
    private final String errorCode;

    public BookNotFoundException(String requestId, String errorCode, String message) {
        super(message);
        this.requestId = requestId;
        this.errorCode = errorCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getErrorCode() {
        return errorCode;
    }
}