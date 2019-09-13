package vsu.labs.crypto.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import vsu.labs.crypto.entity.User;
import vsu.labs.crypto.enums.RoleType;

@Data
@AllArgsConstructor
public class AuthResponse {
    private User user;
    private RoleType role;
}
