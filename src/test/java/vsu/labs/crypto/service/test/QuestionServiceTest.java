package vsu.labs.crypto.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vsu.labs.crypto.config.IntegrationTest;
import vsu.labs.crypto.enums.TaskType;

public class QuestionServiceTest extends IntegrationTest {
    @Autowired
    private QuestionService service;

    @Test
    public void name() {
        System.out.println(service.getAllQuestionByType(TaskType.SELECT));
    }
}