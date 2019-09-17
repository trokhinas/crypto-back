package vsu.labs.crypto.entity.test;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class QuestionEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "question")
    private List<AnswerEntity> answerList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private TaskEntity task;
}
