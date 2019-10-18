package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.dto.test.TaskDto;
import vsu.labs.crypto.service.test.TaskService;


@RestController
@RequestMapping("task")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("options")
    public Response getAll(){
        return Response.success(taskService.getAll()) ;
    }

    @PostMapping
    public Boolean createTask(@RequestBody TaskDto taskDto) throws Exception {
        return taskService.createTask(taskDto);
    }
}
