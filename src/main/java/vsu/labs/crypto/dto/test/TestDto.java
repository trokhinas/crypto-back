package vsu.labs.crypto.dto.test;

import lombok.Data;
import org.springframework.scheduling.config.Task;
import vsu.labs.crypto.entity.test.TaskEntity;

import java.util.List;

@Data
public class TestDto {
    private Long testId;
    private List<TaskDto> tasks;
}
