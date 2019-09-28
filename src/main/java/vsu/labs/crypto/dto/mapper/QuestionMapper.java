package vsu.labs.crypto.dto.mapper;

import org.mapstruct.Mapper;
import vsu.labs.crypto.dto.test.QuestionDto;
import vsu.labs.crypto.entity.test.QuestionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionDto toDto(QuestionEntity questionEntity);

    QuestionEntity fromDto(QuestionDto questionDto);

    List<QuestionDto> toDto(List<QuestionEntity> questionEntities);

    List<QuestionEntity> fromDto(List<QuestionDto> questionDtos);
}
