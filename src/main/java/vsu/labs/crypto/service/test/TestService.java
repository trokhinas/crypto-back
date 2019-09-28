package vsu.labs.crypto.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.AnswerMapper;
import vsu.labs.crypto.dto.mapper.QuestionMapper;
import vsu.labs.crypto.dto.mapper.TaskMapper;
import vsu.labs.crypto.dto.mapper.TestMapper;
import vsu.labs.crypto.dto.test.TestDto;
import vsu.labs.crypto.entity.JpaRepository.AnswerRepository;
import vsu.labs.crypto.entity.JpaRepository.QuestionRepository;
import vsu.labs.crypto.entity.JpaRepository.TaskRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.test.TestEntity;

@Service
public class TestService {
    private static final Logger log = LoggerFactory.getLogger(TestService.class);
   private final TestRepository testRepository;
   private final TaskRepository taskRepository;
   private final QuestionRepository questionRepository;
   private final AnswerRepository answerRepository;
   private final TestMapper testMapper;
   private final TaskMapper taskMapper;
   private final QuestionMapper questionMapper;
   private final AnswerMapper answerMapper;
    @Autowired
    public TestService(TestRepository testRepository, TaskRepository taskRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, TestMapper testMapper, TaskMapper taskMapper, QuestionMapper questionMapper, AnswerMapper answerMapper) {
        this.testRepository = testRepository;
        this.taskRepository = taskRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.testMapper = testMapper;
        this.taskMapper = taskMapper;
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
    }
    public boolean addTest(TestDto testDto){
        TestEntity testOnSave = testMapper.fromDto(testDto);
        TestEntity saveTest = testRepository.save(testOnSave);
        if (saveTest == null)
            return false;
        else return true;
    }
}
