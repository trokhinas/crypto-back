package vsu.labs.crypto.dto.mapper;


import org.mapstruct.Mapper;
import vsu.labs.crypto.dto.test.TaskDto;
import vsu.labs.crypto.entity.test.TaskEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(TaskEntity taskEntity);

    TaskEntity fromDto(TaskDto taskDto);

    List<TaskDto> toDto(List<TaskEntity> taskEntities);

    List<TaskEntity> fromDto(List<TaskDto> taskDtos);
}
