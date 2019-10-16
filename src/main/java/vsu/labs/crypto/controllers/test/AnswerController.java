package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.test.CheckedTask;
import vsu.labs.crypto.dto.test.QuestionDto;
import vsu.labs.crypto.dto.test.TaskAnswerDto;
import vsu.labs.crypto.enums.TaskType;
import vsu.labs.crypto.service.test.AnswerService;
import vsu.labs.crypto.service.test.QuestionService;

import java.util.List;

@RestController
@RequestMapping("answer")
@AllArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    @PostMapping
    public CheckedTask checkTask(@RequestParam("userId") long id,@RequestBody List<TaskAnswerDto> taskAnswerList){
        return answerService.checkTask(id,taskAnswerList);
    }

}
