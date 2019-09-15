package vsu.labs.crypto.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import vsu.labs.crypto.entity.UserEntity;
import vsu.labs.crypto.enums.RoleType;

@Data
@AllArgsConstructor
public class AuthResponse {
    private UserEntity user;
    private RoleType role;
}
