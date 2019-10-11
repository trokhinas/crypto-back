package vsu.labs.crypto.dto.test;

import lombok.Data;
import vsu.labs.crypto.enums.TaskType;

@Data
public class TaskDto {
    private Long taskId;
    private TaskType type;
    private QuestionDto question;
}
