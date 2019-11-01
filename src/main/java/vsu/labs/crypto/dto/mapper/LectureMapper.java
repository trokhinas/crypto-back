package vsu.labs.crypto.dto.mapper;


import org.mapstruct.Mapper;
import vsu.labs.crypto.dto.test.LectureDto;
import vsu.labs.crypto.entity.test.LectureEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface LectureMapper {

    LectureDto toDto(LectureEntity testEntity);

    LectureEntity fromDto(LectureDto testDto);

    List<LectureDto> toDto(List<LectureEntity> testEntities);

    List<LectureEntity> fromDto(List<LectureDto> testDtos);
}
