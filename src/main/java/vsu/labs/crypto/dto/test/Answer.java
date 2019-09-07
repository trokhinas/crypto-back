package vsu.labs.crypto.dto.test;

import lombok.Data;

@Data
public class Answer {
    private Long answerId;
    private String text;
    private boolean correct;
}
