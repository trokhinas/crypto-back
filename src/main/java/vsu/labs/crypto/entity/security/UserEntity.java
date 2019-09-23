package vsu.labs.crypto.entity.security;

import lombok.Data;
import vsu.labs.crypto.entity.test.CourseEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;


    @Column(name = "login")
    private String login;


    @Column(name = "password")
    private String password;


    @Column(name = "role_id")
    private long roleId;

    @ManyToMany(mappedBy = "users")
    private List<CourseEntity> courses;

}
