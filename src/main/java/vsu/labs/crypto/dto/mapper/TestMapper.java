package vsu.labs.crypto.dto.mapper;

import org.mapstruct.Mapper;
import vsu.labs.crypto.dto.test.TestDto;
import vsu.labs.crypto.entity.test.TestEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {
    TestDto toDto(TestEntity testEntity);

    TestEntity fromDto(TestDto testDto);

    List<TestDto> toDto(List<TestEntity> testEntities);

    List<TestEntity> fromDto(List<TestDto> testDtos);
}

