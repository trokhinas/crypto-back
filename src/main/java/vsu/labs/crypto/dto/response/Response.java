package vsu.labs.crypto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import vsu.labs.crypto.enums.ResponseStatus;

import static vsu.labs.crypto.enums.ResponseStatus.*;

@Data @AllArgsConstructor
public class Response {

    private final Object data;
    private final ResponseStatus status;
    private final String message;

    public static Response success(Object data, String message) {
        return new Response(data, OK, message);
    }

    public static Response success(String message) {
        return new Response(null, OK, message);
    }

    public static Response success() {
        return new Response(null, OK, null);
    }

    public static Response fail(String message) {
        return new Response(null, ERROR, message);
    }

    public static Response fail(Object errorDetails, String message) {
        return new Response(errorDetails, ERROR, message);
    }
}
