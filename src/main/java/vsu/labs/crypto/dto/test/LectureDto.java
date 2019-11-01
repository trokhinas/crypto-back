package vsu.labs.crypto.dto.test;

import lombok.Data;

@Data
public class LectureDto {
    private Long id;

    private String name;

    private String reference;

    private UserDto author;
}
