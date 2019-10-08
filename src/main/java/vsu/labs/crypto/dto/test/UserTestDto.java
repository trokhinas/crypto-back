package vsu.labs.crypto.dto.test;

import lombok.Data;

@Data
public class UserTestDto {
    private TestDto test;
    private Integer correctAnswer;
    private Integer allQuestion;
}
