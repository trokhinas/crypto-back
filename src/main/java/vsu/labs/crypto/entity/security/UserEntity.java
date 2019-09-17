package vsu.labs.crypto.entity.security;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
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


}
