package vsu.labs.crypto.entity;

import lombok.Data;

import javax.persistence.*;



@Entity
@Data
public class Test {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

}

