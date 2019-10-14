package vsu.labs.crypto.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vsu.labs.crypto.dto.test.AnswerDto;
import vsu.labs.crypto.dto.test.TaskDto;
import vsu.labs.crypto.entity.test.AnswerEntity;
import vsu.labs.crypto.entity.test.TaskEntity;

import java.util.List;

@Mapper(componentModel = "spring",uses = TaskTypeMapper.class)
public interface AnswerMapper {
    @Mappings({
            @Mapping(source = "id", target = "answerId"),
            @Mapping(source = "name", target = "text"),
            @Mapping(source = "isCorrect", target = "correct")
    })
    AnswerDto toDto(AnswerEntity answerEntity);
    @Mappings({
            @Mapping(target = "id", source = "answerId"),
            @Mapping(target = "name", source = "text"),
            @Mapping(target = "isCorrect", source = "correct")
    })
    AnswerEntity fromDto(AnswerDto answerDto);

    List<AnswerDto> toDto(List<AnswerEntity> answerEntities);

    List<AnswerEntity> fromDto(List<AnswerDto> answerDtos);
}
