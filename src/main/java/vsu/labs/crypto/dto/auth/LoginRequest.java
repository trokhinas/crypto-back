package vsu.labs.crypto.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private String login;
    private String password;
}
