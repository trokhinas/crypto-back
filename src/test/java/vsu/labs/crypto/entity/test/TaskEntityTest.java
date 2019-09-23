package vsu.labs.crypto.entity.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vsu.labs.crypto.config.IntegrationTest;
import vsu.labs.crypto.entity.JpaRepository.AnswerRepository;
import vsu.labs.crypto.entity.JpaRepository.QuestionRepository;
import vsu.labs.crypto.entity.JpaRepository.TaskRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.enums.TaskType;

public class TaskEntityTest extends IntegrationTest {
    @Autowired
    TestRepository testRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Test
    public void test() {
        TestEntity testEntity = new TestEntity();
        testEntity.setTitle("first");
        TestEntity savedTest = testRepository.save(testEntity);

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName("firstOfFirst");
        taskEntity.setType("input");
        taskEntity.setTest(savedTest);
        TaskEntity savedTask = taskRepository.save(taskEntity);

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setName("firstOfFirstOfFirst");
        questionEntity.setTask(savedTask);
        QuestionEntity savedQuestion = questionRepository.save(questionEntity);

        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setName("firstOfFirstOfFirstOfFirst");
        answerEntity.setIsCorrect(true);
        answerEntity.setQuestion(savedQuestion);
        AnswerEntity savedAnswer = answerRepository.save(answerEntity);

        answerRepository.delete(savedAnswer);
        questionRepository.delete(savedQuestion);
        taskRepository.delete(savedTask);
        testRepository.delete(savedTest);
    }
}