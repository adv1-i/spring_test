package com.example.springrest.FirstRestApp.util;

public class PersonErrorResponse {
    private String message;
    private long timestamp;
    private int statusCode;

    public PersonErrorResponse(String message, long timestamp, int statusCode) {
        this.message = message;
        this.timestamp = timestamp;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
