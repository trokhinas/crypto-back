package vsu.labs.crypto.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class User {

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
