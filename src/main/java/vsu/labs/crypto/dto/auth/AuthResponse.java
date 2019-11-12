package vsu.labs.crypto.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import vsu.labs.crypto.dto.test.UserDto;
import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.enums.RoleType;

@Data
@AllArgsConstructor
// TODO избавиться от сущности в Response, добавить DTO
public class AuthResponse {
    private UserDto user;
    private RoleType role;
}
