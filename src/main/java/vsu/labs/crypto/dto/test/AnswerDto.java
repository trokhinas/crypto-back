package vsu.labs.crypto.dto.test;

import lombok.Data;

@Data
public class AnswerDto {
    private Long answerId;
    private String text;
    private boolean correct;
}
