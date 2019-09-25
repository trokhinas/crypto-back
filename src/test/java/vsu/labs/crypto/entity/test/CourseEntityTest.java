package vsu.labs.crypto.entity.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import vsu.labs.crypto.config.IntegrationTest;
import vsu.labs.crypto.entity.JpaRepository.CourseRepository;
import vsu.labs.crypto.entity.JpaRepository.LectureRepository;
import vsu.labs.crypto.entity.JpaRepository.RoleRepository;
import vsu.labs.crypto.entity.JpaRepository.UserRepository;
import vsu.labs.crypto.entity.security.RoleEntity;
import vsu.labs.crypto.entity.security.UserEntity;
import vsu.labs.crypto.enums.RoleType;

import java.util.Arrays;
import java.util.Collections;


public class CourseEntityTest extends IntegrationTest {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    LectureRepository lectureRepository;
    @Test
    public void name() {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("trololo");
        userEntity.setName("troll");
        userEntity.setPassword("trolololol");
        userEntity.setRoleId(RoleType.ADMIN.getId());
        userEntity.setSurname("lol");

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName("first");
        courseEntity.getUsers().add(userEntity);
        CourseEntity courseSaved = courseRepository.save(courseEntity);

        LectureEntity lectureEntity = new LectureEntity();
        lectureEntity.setName("asdf");
        lectureEntity.setReference("asdf");
        lectureEntity.setCourse(courseSaved);
        LectureEntity savedCourseThemes = lectureRepository.save(lectureEntity);

        lectureRepository.delete(savedCourseThemes);
        courseRepository.delete(courseSaved);
    }
}