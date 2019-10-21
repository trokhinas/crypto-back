package vsu.labs.crypto.controllers.test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vsu.labs.crypto.dto.response.Response;
import vsu.labs.crypto.dto.test.QuestionDto;
import vsu.labs.crypto.enums.TaskType;
import vsu.labs.crypto.service.test.QuestionService;


@RestController
@RequestMapping("question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("all")
    public Response allQuestionType(@RequestParam TaskType type){
        return Response.success(questionService.getAllQuestionByType(type));
    }

    @GetMapping("options")
    public Response getAll(@RequestParam(required = false) TaskType taskType){
        if (taskType != null)
            return Response.success(questionService.getOptionsByType(taskType));
        return Response.success(questionService.getAll());
    }


    @PostMapping
    public boolean createQuest(@RequestBody QuestionDto questionDto) throws Exception {
        return questionService.createQuest(questionDto);
    }
}
