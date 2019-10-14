package vsu.labs.crypto.dto.test;

import lombok.Data;
import vsu.labs.crypto.enums.TaskType;

@Data
public class TaskTypeDto {
    private Long id;
    private String title;
    private TaskType taskType;

}
