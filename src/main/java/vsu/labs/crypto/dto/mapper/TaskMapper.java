package vsu.labs.crypto.dto.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vsu.labs.crypto.dto.test.TaskDto;
import vsu.labs.crypto.entity.test.TaskEntity;
import vsu.labs.crypto.entity.test.TaskTypeEntity;
import vsu.labs.crypto.enums.TaskType;

import java.util.List;

@Mapper(componentModel = "spring", uses = QuestionMapper.class)
public interface TaskMapper {

    @Mappings({
            @Mapping(source = "id", target = "taskId"),
            @Mapping(source = "taskType", target = "type")
    })
    TaskDto toDto(TaskEntity taskEntity);

    @Mappings({
            @Mapping(target = "id", source = "taskId"),
            @Mapping(source = "type",target = "taskType")
    })
    TaskEntity fromDto(TaskDto taskDto);

    List<TaskDto> toDto(List<TaskEntity> taskEntities);

    List<TaskEntity> fromDto(List<TaskDto> taskDtos);


    default TaskType map(TaskTypeEntity value) {
        return value.getTaskType();
    }
}
