package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.test.Answer;
import vsu.labs.crypto.dto.test.Question;
import vsu.labs.crypto.dto.test.Task;
import vsu.labs.crypto.dto.test.Test;
import vsu.labs.crypto.entity.AnswerEntity;
import vsu.labs.crypto.entity.JpaRepository.AnswerRepository;
import vsu.labs.crypto.entity.JpaRepository.QuestionRepository;
import vsu.labs.crypto.entity.JpaRepository.TaskRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.QuestionEntity;
import vsu.labs.crypto.entity.TaskEntity;
import vsu.labs.crypto.entity.TestEntity;
import vsu.labs.crypto.utils.PropertyMapper;

import java.util.List;

@Service @AllArgsConstructor
public class TestService {
    private static final Logger log = LoggerFactory.getLogger(TestService.class);

    private TestRepository testRepository;
    private TaskRepository taskRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public void createTest(Test test) {
        log.info("process method create test with test = {}", test);
        TestEntity entity = new TestEntity();
        PropertyMapper.map(test, entity);

        TestEntity savedEntity = testRepository.save(entity);
        test.getTasks().forEach(task -> createTask(task, savedEntity));
    }

    private void createTask(Task task, TestEntity test) {
        log.info("process method createTask");
        TaskEntity entity = new TaskEntity();
        PropertyMapper.map(task, entity);
        entity.setTest(test);

        TaskEntity savedEntity = taskRepository.save(entity);
        task.getQuestions().forEach(question -> createQuestion(question, savedEntity));
    }

    private void createQuestion(Question question, TaskEntity task) {
        log.info("process method create question");
        QuestionEntity entity = new QuestionEntity();
        PropertyMapper.map(question, entity);
        entity.setTask(task);

        QuestionEntity savedEntity = questionRepository.save(entity);
        question.getAnswers().forEach(answer -> createAnswer(answer, savedEntity));
    }

    private void createAnswer(Answer answer, QuestionEntity question) {
        AnswerEntity entity = new AnswerEntity();
        PropertyMapper.map(answer, entity);
        entity.setQuestion(question);

        answerRepository.save(entity);
    }
}
