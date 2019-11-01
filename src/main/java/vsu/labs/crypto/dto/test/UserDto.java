package vsu.labs.crypto.dto.test;

import lombok.Data;
import vsu.labs.crypto.entity.test.CourseEntity;
import vsu.labs.crypto.entity.test.LectureEntity;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class UserDto {
    private Long id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private long roleId;

}
