package vsu.labs.crypto.service.users.util;

import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor
public class ObjectDataExtractor<E> {
    private String key;
    private Function<E, Object> extractor;

    public String getKey() {
        return key;
    }

    public Function<E, Object> getExtractor() {
        return extractor;
    }
}
