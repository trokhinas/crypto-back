package vsu.labs.crypto.dto.mapper;

import org.mapstruct.Mapper;
import vsu.labs.crypto.dto.test.AnswerDto;
import vsu.labs.crypto.dto.test.TaskDto;
import vsu.labs.crypto.entity.test.AnswerEntity;
import vsu.labs.crypto.entity.test.TaskEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    AnswerDto toDto(AnswerEntity answerEntity);

    AnswerEntity fromDto(AnswerDto answerDto);

    List<AnswerDto> toDto(List<AnswerEntity> answerEntities);

    List<AnswerEntity> fromDto(List<AnswerDto> answerDtos);
}
