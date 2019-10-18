package vsu.labs.crypto.service.test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vsu.labs.crypto.dto.mapper.TaskMapper;
import vsu.labs.crypto.dto.test.TaskDto;
import vsu.labs.crypto.dto.test.OptionDto;
import vsu.labs.crypto.entity.JpaRepository.TaskRepository;
import vsu.labs.crypto.entity.test.TaskEntity;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private static final int SHORT_NAME_LENGTH = 60;

    public boolean createTask(TaskDto taskDto) throws Exception {
        TaskEntity createdTask = taskRepository.save(taskMapper.fromDto(taskDto));
        if (createdTask == null)
            throw new Exception("Произошла ошибка в сохранении таска");
        return true;
    }

    public List<OptionDto<TaskDto>> getAll() {
        List<TaskEntity> allTask = taskRepository.findAll();
        List<OptionDto<TaskDto>> allTaskDto = new ArrayList<>();
        for (TaskEntity currentTask : allTask) {
            OptionDto<TaskDto> newTaskDto = new OptionDto<>();
            newTaskDto.setValue(taskMapper.toDto(currentTask));
            newTaskDto.setLabel(generateLabel(currentTask));// лейбл это первые 30 символов вопроса,на который он указывает
            allTaskDto.add(newTaskDto);
        }
        return allTaskDto;
    }

    private String generateLabel(TaskEntity currentTask) {
        String questionText = currentTask.getQuestion().getName();
        return questionText.length() > SHORT_NAME_LENGTH ? questionText.substring(0, SHORT_NAME_LENGTH - 1) : questionText;
    }
}
