package vsu.labs.crypto.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Task {

    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "task")
    private List<Question> questionList;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

}
