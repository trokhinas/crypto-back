package vsu.labs.crypto.dto.mapper;

import org.mapstruct.Mapper;
import vsu.labs.crypto.dto.test.TestDto;
import vsu.labs.crypto.dto.test.UserDto;
import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.entity.test.TestEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity userEntity);

    UserEntity fromDto(UserDto userDto);

    List<UserDto> toDto(List<UserEntity> userEntities);

    List<UserEntity> fromDto(List<UserDto> userDtos);
}
