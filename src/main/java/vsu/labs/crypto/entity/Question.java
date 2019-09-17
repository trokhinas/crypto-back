package vsu.labs.crypto.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Question {

    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;
}
