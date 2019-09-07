package vsu.labs.crypto.dto.response;

import vsu.labs.crypto.enums.ResponseStatus;

import static vsu.labs.crypto.enums.ResponseStatus.*;


public class Response {

    private Object data;
    private ResponseStatus status;
    private String message;

    private Response(Object data, ResponseStatus status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static Response success(Object data, String message) {
        return new Response(data, OK, message);
    }

    public static Response success(String message) {
        return new Response(null, OK, message);
    }

    public static Response fail(String message) {
        return new Response(null, ERROR, message);
    }

    public static Response fail(Object errorDetails, String message) {
        return new Response(errorDetails, ERROR, message);
    }
}
