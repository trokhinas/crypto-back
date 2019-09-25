package vsu.labs.crypto.entity.test;

import lombok.Data;
import vsu.labs.crypto.entity.security.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "course")
@Data
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "subscribed_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<LectureEntity> lectureEntityList = new ArrayList<>();
}
