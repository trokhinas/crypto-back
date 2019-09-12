package vsu.labs.crypto.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Test {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "test")
    private List<Test> test;

}

