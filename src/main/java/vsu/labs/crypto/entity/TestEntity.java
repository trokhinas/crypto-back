package vsu.labs.crypto.entity;

import lombok.Data;

import javax.persistence.*;



@Entity
@Data
public class TestEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

}

