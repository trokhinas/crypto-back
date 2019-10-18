package vsu.labs.crypto.dto.test;

import lombok.Data;

@Data
public class OptionDto<V> {
    private String label;
    private V value;
}
