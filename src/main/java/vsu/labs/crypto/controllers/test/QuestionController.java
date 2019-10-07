package vsu.labs.crypto.controllers.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vsu.labs.crypto.dto.test.QuestionDto;
import vsu.labs.crypto.enums.TaskType;
import vsu.labs.crypto.service.test.QuestionService;

import java.util.List;

@RestController("/question")
public class QuestionController {
    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public List<QuestionDto> allQuestionType(@RequestParam TaskType type){
        return questionService.getAllQustionOfType(type);
    }
}
