package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.test.CheckedTask;
import vsu.labs.crypto.dto.test.TaskAnswerDto;
import vsu.labs.crypto.dto.test.TaskDto;
import vsu.labs.crypto.entity.JpaRepository.MarkRepository;
import vsu.labs.crypto.entity.JpaRepository.QuestionRepository;
import vsu.labs.crypto.entity.test.AnswerEntity;
import vsu.labs.crypto.entity.test.MarkEntity;
import vsu.labs.crypto.entity.test.QuestionEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AnswerService {
    private final QuestionRepository questionRepository;
    private final MarkRepository markRepository;

    public CheckedTask checkTask(Long userId, Long testId, List<TaskAnswerDto> request) {
        if (request == null)
            return null;
        int trueAnswer = 0;
        for (TaskAnswerDto current : request) {
            trueAnswer += checkAnswer(current);
        }
        MarkEntity markOfUser = new MarkEntity();
        markOfUser.setAll_question(request.size());//вообще должно быть количество в тесте,но в целом прикольно если будет показываться,сколько правилно из того что прошёл(лентяй я// )
        markOfUser.setCorrectAnswer(trueAnswer);
        markOfUser.setUserId(userId);
        markOfUser.setTestId(testId);
        markRepository.save(markOfUser);
        String response = "Вы ответили правильно на"+Integer.toString(trueAnswer)+ " из "+ Integer.toString(request.size())+" вопросов";
        return new CheckedTask(response);
    }

    public int checkAnswer(TaskAnswerDto taskAnswer) {
        TaskDto taskDto = taskAnswer.getTaskDto();
        switch (taskDto.getType()) {
            case SELECT: {
                List<Long> answerOfUser = (List<Long>) taskAnswer.getValue();
                QuestionEntity question = questionRepository.findById(taskDto.getQuestion().getQuestionId()).get();
                List<AnswerEntity> answers = question.getAnswerList();
                for (AnswerEntity answer : answers) {
                    if (answer.getIsCorrect() && answer.getId().equals(answerOfUser.get(0)))
                        return 1;
                }
            }
            case MULTISELECT: {
                List<Long> answerOfUser = (List<Long>) taskAnswer.getValue();
                QuestionEntity question = questionRepository.findById(taskDto.getQuestion().getQuestionId()).get();
                List<AnswerEntity> answers = question.getAnswerList();
                List<Long> correctAnswer = new ArrayList<>();
                for (AnswerEntity answer : answers) {
                    if (answer.getIsCorrect())
                        correctAnswer.add(answer.getId());
                }
                int counterOfTrueAnswer = 0;
                for (int i = 0; i < answerOfUser.size(); i++) {
                    for (int j = 0; j < correctAnswer.size(); j++) {
                        if (answerOfUser.get(i).equals(correctAnswer.get(i)))
                            counterOfTrueAnswer++;
                    }
                }
                if (answerOfUser.size() == counterOfTrueAnswer)
                    return 1;
            }
            case MANUAL: {
                QuestionEntity question = questionRepository.findById(taskDto.getQuestion().getQuestionId()).get();
                List<AnswerEntity> answers = question.getAnswerList();
                if (answers.get(0).getName().equals(taskAnswer.getValue()))
                    return 1;
            }
            return 0;
        }
        return 0;
    }
}
