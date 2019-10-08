package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vsu.labs.crypto.dto.test.QuestionDto;
import vsu.labs.crypto.entity.test.QuestionEntity;
import vsu.labs.crypto.enums.RoleType;
import vsu.labs.crypto.enums.TaskType;
import vsu.labs.crypto.service.test.QuestionService;

import java.util.List;

@RestController
@RequestMapping("question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("all")
    public List<QuestionEntity> allQuestionType(){
        return questionService.getAllQuestionByType(TaskType.SELECT);
    }
}
