package vsu.labs.crypto.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class TaskEntity {

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
    private List<QuestionEntity> questionList;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private TestEntity test;

}
