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
    public List<OptionDto<TaskDto>> getAll(){
        List<TaskEntity> allTask = taskRepository.findAll();
        List<OptionDto<TaskDto>> allTaskDto = new ArrayList<>();
        for (TaskEntity currentTask: allTask){
            OptionDto<TaskDto> newTaskDto = new OptionDto<>();
            newTaskDto.setValue(taskMapper.toDto(currentTask));
            newTaskDto.setLabel(currentTask.getQuestion().getName().substring(0,29));// лейбл это первые 30 символов вопроса,на который он указывает
            allTaskDto.add(newTaskDto);
        }
        return allTaskDto;
    }
}
