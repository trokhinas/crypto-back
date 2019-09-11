package vsu.labs.crypto.dto.crypto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class StageData {

    private String message;
    private Object data;

    public static StageData message(String message) {
        return new StageData(message, null);
    }

    public static StageData withData(String message, Object data) {
        return new StageData(message, data);
    }
}
