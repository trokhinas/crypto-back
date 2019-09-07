package vsu.labs.crypto.dto.test;

import lombok.Data;
import vsu.labs.crypto.enums.TaskType;

import java.util.List;

@Data
public class Task {
    private Long taskId;
    private TaskType type;
    private List<Question> questions;
}
