package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.QuestionMapper;
import vsu.labs.crypto.dto.test.QuestionDto;
import vsu.labs.crypto.entity.JpaRepository.AnswerRepository;
import vsu.labs.crypto.entity.JpaRepository.QuestionRepository;
import vsu.labs.crypto.entity.test.AnswerEntity;
import vsu.labs.crypto.entity.test.QuestionEntity;
import vsu.labs.crypto.enums.TaskType;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionMapper questionMapper;

    public List<QuestionEntity> getAllQuestionByType(TaskType type) {
        List<QuestionEntity> allQuestion = questionRepository.findAll();
        List<QuestionEntity> result = new ArrayList<>();
        switch (type) {
            case SELECT: {
                for (int i = 0; i < allQuestion.size(); i++) {
                    QuestionEntity curQuestion = allQuestion.get(i);
                    List<AnswerEntity> currentAnswerList = answerRepository.findByQuestionId(Math.toIntExact(curQuestion.getId()));
                    if (currentAnswerList.size() <= 1) {
                        continue;
                    }
                    boolean check = false;
                    for (AnswerEntity answer : currentAnswerList) {
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
            case MANUAL: {
                for (int i = 0; i < allQuestion.size(); i++) {
                    QuestionEntity curQuestion = allQuestion.get(i);
                    if (curQuestion.getAnswerList().size() != 1) {
                        break;
                    } else
                        result.add(curQuestion);
                }
            }
            case MULTISELECT: {
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
        for (QuestionEntity x : result)
            System.out.println(x.getName());
        List<QuestionDto> resultDto = questionMapper.toDto(result);
        for (QuestionDto x : resultDto)
            System.out.println(x.getText());
        return result;

    }
}
