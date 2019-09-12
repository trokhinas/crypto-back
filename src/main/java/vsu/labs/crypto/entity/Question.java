package vsu.labs.crypto.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Question {

    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;
}