package vsu.labs.crypto.dto.test;

import lombok.Data;
import vsu.labs.crypto.enums.TaskType;

@Data
public class TaskAnswerDto {
    private Long taskId;
    private Long testId;
    private TaskType type;
    private QuestionDto question;
    private Object value;
}
