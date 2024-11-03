package com.server.JavaServer.exception;

import lombok.Data;

@Data
public class HttpExceptionMessage {
    private int status;
    private String message;
    private String stackTrace;
    private long timestamp;

    public HttpExceptionMessage(int status, String stackTrace, String message) {
        this.status = status;
        this.stackTrace = stackTrace;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

}