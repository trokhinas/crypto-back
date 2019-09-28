package vsu.labs.crypto.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.test.Test;
import vsu.labs.crypto.entity.JpaRepository.AnswerRepository;
import vsu.labs.crypto.entity.JpaRepository.QuestionRepository;
import vsu.labs.crypto.entity.JpaRepository.TaskRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.test.TestEntity;

import java.util.List;

@Service
public class TestService {
    private static final Logger log = LoggerFactory.getLogger(TestService.class);
   private final TestRepository testRepository;
   private final TaskRepository taskRepository;
   private final QuestionRepository questionRepository;
   private final AnswerRepository answerRepository;
    @Autowired
    public TestService(TestRepository testRepository, TaskRepository taskRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.testRepository = testRepository;
        this.taskRepository = taskRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }
    public boolean addTest(Test test){
        return false;
    }
}
