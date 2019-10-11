package vsu.labs.crypto.dto.mapper;

import org.mapstruct.Mapper;
import vsu.labs.crypto.dto.test.TaskTypeDto;
import vsu.labs.crypto.entity.test.TaskTypeEntity;

@Mapper(componentModel = "spring")
public interface  TaskTypeMapper {

    TaskTypeDto toDto(TaskTypeEntity taskTypeEntity);

    TaskTypeEntity fromDto(TaskTypeDto taskTypeDto);
}
