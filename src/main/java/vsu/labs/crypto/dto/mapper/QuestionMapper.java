package vsu.labs.crypto.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vsu.labs.crypto.dto.test.QuestionDto;
import vsu.labs.crypto.entity.test.QuestionEntity;

import java.util.List;

@Mapper(componentModel = "spring",uses = AnswerMapper.class)
public interface QuestionMapper {

    @Mappings({
            @Mapping(source = "id", target = "questionId"),
            @Mapping(source = "name", target = "text"),
            @Mapping(source = "answerList", target = "answers")
    })
    QuestionDto toDto(QuestionEntity questionEntity);
    @Mappings({
            @Mapping(target = "id", source = "questionId"),
            @Mapping(target = "name", source = "text"),
            @Mapping(target = "answerList", source = "answers")
    })
    QuestionEntity fromDto(QuestionDto questionDto);

    List<QuestionDto> toDto(List<QuestionEntity> questionEntities);

    List<QuestionEntity> fromDto(List<QuestionDto> questionDtos);
}
