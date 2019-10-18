package vsu.labs.crypto.dto.test;

import lombok.Data;

@Data
public class OptionDto<T> {
    T value;
    String label;
}
