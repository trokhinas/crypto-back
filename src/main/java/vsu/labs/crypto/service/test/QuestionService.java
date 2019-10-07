package vsu.labs.crypto.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.QuestionMapper;
import vsu.labs.crypto.dto.test.QuestionDto;
import vsu.labs.crypto.entity.JpaRepository.QuestionRepository;
import vsu.labs.crypto.entity.test.AnswerEntity;
import vsu.labs.crypto.entity.test.QuestionEntity;
import vsu.labs.crypto.enums.TaskType;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }


    public List<QuestionDto> getAllQustionOfType(TaskType type) {
        List<QuestionEntity> allQuestion = questionRepository.findAll();
        List<QuestionEntity> result = new ArrayList<>();
        switch (type) {
            case SELECT: {
                findSelectQuestion(allQuestion,result);
            }
            case MANUAL: {
                findManualQuestion(allQuestion,result);
            }
            case MULTISELECT: {
                findMultiselectQuestion(allQuestion,result);
            }
        }
        return questionMapper.toDto(result);
    }

    private void findSelectQuestion(List<QuestionEntity> allQuestion, List<QuestionEntity> result) {
        for (int i = 0; i < allQuestion.size(); i++) {
            QuestionEntity curQuestion = allQuestion.get(i);
            if (curQuestion.getAnswerList().size() <= 1) {
                continue;
            }
            boolean check = false;
            for (AnswerEntity answer : curQuestion.getAnswerList()) {
                if (answer.getIsCorrect()) {
                    if (check) {
                        check = false;
                        break;
                    } else
                        check = true;
                }
            }
            if (check)
                result.add(curQuestion);
        }
    }

    private void findManualQuestion(List<QuestionEntity> allQuestion, List<QuestionEntity> result) {
        for (int i = 0; i < allQuestion.size(); i++) {
            QuestionEntity curQuestion = allQuestion.get(i);
            if (curQuestion.getAnswerList().size() != 1) {
                break;
            } else
                result.add(curQuestion);
        }
    }

    private void findMultiselectQuestion(List<QuestionEntity> allQuestion, List<QuestionEntity> result) {
        for (int i = 0; i < allQuestion.size(); i++) {
            QuestionEntity curQuestion = allQuestion.get(i);
            if (curQuestion.getAnswerList().size() <= 1) {
                continue;
            }
            int count = 0;
            for (AnswerEntity answer : curQuestion.getAnswerList()) {
                if (answer.getIsCorrect()) {
                    count++;
                }
            }
            if (count > 1)
                result.add(curQuestion);
        }
    }
}