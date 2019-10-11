package vsu.labs.crypto.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vsu.labs.crypto.dto.test.TestDto;
import vsu.labs.crypto.entity.test.TestEntity;

import java.util.List;

@Mapper(componentModel = "spring",uses = {TaskMapper.class})
public interface TestMapper {

    @Mappings({
            @Mapping(target = "testId", source = "id")
    })
    TestDto toDto(TestEntity testEntity);

    @Mappings({
            @Mapping(source = "testId", target = "id")
    })
    TestEntity fromDto(TestDto testDto);


    List<TestDto> toDto(List<TestEntity> testEntities);

    List<TestEntity> fromDto(List<TestDto> testDtos);
}

