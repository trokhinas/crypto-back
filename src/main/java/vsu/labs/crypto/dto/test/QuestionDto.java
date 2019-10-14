package vsu.labs.crypto.dto.test;

import lombok.Data;
import vsu.labs.crypto.entity.test.AnswerEntity;

import java.util.List;

@Data
public class QuestionDto {
    private Long questionId;
    private String text;
    private List<AnswerDto> answers;
}
