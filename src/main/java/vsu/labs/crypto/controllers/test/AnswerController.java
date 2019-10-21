package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.test.CheckedTask;
import vsu.labs.crypto.dto.test.TaskAnswerDto;
import vsu.labs.crypto.service.test.AnswerService;

import java.util.List;

@RestController
@RequestMapping("answer")
@AllArgsConstructor @Slf4j
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public CheckedTask checkTask(@RequestParam Long userId,
                                 @RequestParam Long testId,
                                 @RequestBody List<TaskAnswerDto> taskAnswerList){
        log.info("call checkTask with userId = {}, testId = {}, taskAnswerList = {}", userId, testId, taskAnswerList);
        return answerService.checkTask(userId, testId, taskAnswerList);
    }

}
