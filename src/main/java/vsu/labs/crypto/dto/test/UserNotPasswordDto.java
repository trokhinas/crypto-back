package vsu.labs.crypto.dto.test;

import lombok.Data;

@Data
public class UserNotPasswordDto {
    private Long id;

    private String name;

    private String surname;

    private String login;

    private long roleId;

}
