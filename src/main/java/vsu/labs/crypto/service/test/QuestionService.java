package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.QuestionMapper;
import vsu.labs.crypto.dto.test.OptionDto;
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
    private final QuestionMapper questionMapper;

    public boolean createQuest(QuestionDto questionDto) throws Exception {
        QuestionEntity createdQuest = questionRepository.save(questionMapper.fromDto(questionDto));
        if (createdQuest == null) {
            throw new Exception("Не сохранён создаваемый вопрос");
        }
        return true;
    }

    public List<OptionDto<QuestionDto>> getAll() {

        List<QuestionEntity> allQuest = questionRepository.findAll();
        List<OptionDto<QuestionDto>> allQuestDto = new ArrayList<>();
        for (QuestionEntity currentQuest : allQuest) {
            OptionDto<QuestionDto> newTaskDto = new OptionDto<>();
            newTaskDto.setValue(questionMapper.toDto(currentQuest));
            newTaskDto.setLabel(currentQuest.getName().substring(0, 29));// лейбл это первые 30 символов вопроса,на который он указывает
            allQuestDto.add(newTaskDto);
        }
        return allQuestDto;
    }

    public List<QuestionDto> getAllQuestionByType(TaskType type) {
        List<QuestionEntity> allQuestion = questionRepository.findAll();
        List<QuestionEntity> result = new ArrayList<>();
        switch (type) {
            case SELECT: {
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
            case MANUAL: {
                for (int i = 0; i < allQuestion.size(); i++) {
                    QuestionEntity curQuestion = allQuestion.get(i);
                    List<AnswerEntity> answerEntities = curQuestion.getAnswerList();
                    if (answerEntities.size() != 1) {
                        continue;
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
        return questionMapper.toDto(result);

    }
}
