package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.test.TaskDto;
import vsu.labs.crypto.dto.test.OptionDto;
import vsu.labs.crypto.service.test.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public List<OptionDto<TaskDto>> getAll(){
        return taskService.getAll();
    }
    @PostMapping
    public Boolean createTask(@RequestBody TaskDto taskDto) throws Exception {
        return taskService.createTask(taskDto);
    }
}
