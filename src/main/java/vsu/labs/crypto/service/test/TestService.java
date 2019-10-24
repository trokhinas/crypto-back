package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.TestMapper;
import vsu.labs.crypto.dto.test.TestDto;
import vsu.labs.crypto.entity.JpaRepository.AnswerRepository;
import vsu.labs.crypto.entity.JpaRepository.QuestionRepository;
import vsu.labs.crypto.entity.JpaRepository.TaskRepository;
import vsu.labs.crypto.entity.JpaRepository.TestRepository;
import vsu.labs.crypto.entity.security.RoleEntity;
import vsu.labs.crypto.entity.test.*;
import vsu.labs.crypto.enums.RoleType;
import vsu.labs.crypto.enums.TaskType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class TestService {
    private final TestRepository testRepository;
    private final TaskRepository taskRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final TestMapper testMapper;

    public boolean create(TestDto testDto) throws Exception {
        TestEntity createdEntity = testRepository.save(testMapper.fromDto(testDto));
        if (createdEntity == null)
            throw new Exception("Проблема с созданием теста");
        return true;
    }

    public boolean addTest(TestDto testDto) {
        TestEntity testOnSave = testMapper.fromDto(testDto);
        List<TaskEntity> allTaskOfEntity = testOnSave.getTasks();
        List<QuestionEntity> allQuestOfEntity = new ArrayList<>();
        for (TaskEntity task : allTaskOfEntity) {
            allQuestOfEntity.add(task.getQuestion());
        }
        Map<Integer, List<AnswerEntity>> allAnswerOfEntity = new HashMap<>();

        for (int i = 0; i < allQuestOfEntity.size(); i++) {
            List<AnswerEntity> current = allQuestOfEntity.get(i).getAnswerList();
            allAnswerOfEntity.put(i, current);
        }
        testOnSave.setTasks(null);
        TestEntity saveTest = testRepository.save(testOnSave);

        List<QuestionEntity> savedQuestion = new ArrayList<>();
        for (QuestionEntity quest : allQuestOfEntity) {
            quest.setAnswerList(null);
            savedQuestion.add(questionRepository.save(quest));
        }
        for (Map.Entry answerList : allAnswerOfEntity.entrySet()) {
            int number = (int) answerList.getKey();
            for (AnswerEntity answerEntity : allAnswerOfEntity.get(number)) {
                answerEntity.setQuestion(savedQuestion.get(number));
                answerRepository.save(answerEntity);
            }
        }
        List<TaskEntity> savedTask = new ArrayList<>();
        for (int i = 0; i < allTaskOfEntity.size(); i++) {
            allTaskOfEntity.get(i).setId(null);
            allTaskOfEntity.get(i).setTest(saveTest);
            allTaskOfEntity.get(i).setQuestion(savedQuestion.get(i));
            TaskTypeEntity current = allTaskOfEntity.get(i).getTaskType();
            allTaskOfEntity.get(i).setTaskType(null);
            TaskEntity taskWithoutRole=  taskRepository.save(allTaskOfEntity.get(i));
            taskWithoutRole.setTaskType(current);
            taskRepository.save(taskWithoutRole);
        }

        return saveTest != null;
    }

    public List<TestDto> getAll() {
        List<TestEntity> testEntities = testRepository.findAll();
        return testMapper.toDto(testEntities);
    }

    public TestDto getById(Long id) {
        TestEntity testEntity = testRepository.findById(id).get();
        return testMapper.toDto(testEntity);
    }
}
