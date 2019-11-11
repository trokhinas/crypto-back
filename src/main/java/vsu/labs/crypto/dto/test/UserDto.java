package vsu.labs.crypto.dto.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private long roleId;
}
