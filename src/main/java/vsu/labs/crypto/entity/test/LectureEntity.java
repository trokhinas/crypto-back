package vsu.labs.crypto.entity.test;

import lombok.Data;
import org.apache.tomcat.jni.User;
import vsu.labs.crypto.entity.security.UserEntity;

import javax.persistence.*;

@Entity(name = "lecture")
@Data
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lecture_seq")
    @SequenceGenerator(name = "lecture_seq", sequenceName = "lecture_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "reference")
    private String reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity author;
}
