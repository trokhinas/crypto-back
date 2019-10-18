package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.test.OptionDto;
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
    public List<QuestionDto> allQuestionType(@RequestParam TaskType type){
        return questionService.getAllQuestionByType(type);
    }
    @GetMapping("allOption")
    public List<OptionDto<QuestionDto>> getAll(){
        return questionService.getAll();
    }
    @PostMapping
    public boolean createQuest(@RequestBody QuestionDto questionDto) throws Exception {
        return questionService.createQuest(questionDto);
    }
}
