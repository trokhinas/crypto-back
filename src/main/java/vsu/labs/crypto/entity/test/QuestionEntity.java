package vsu.labs.crypto.entity.test;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "question")
@Data
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
    @SequenceGenerator(name = "question_seq", sequenceName = "question_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question")
    private List<AnswerEntity> answerList;

    @OneToMany(mappedBy = "question")
    private List<TaskEntity> taskList;

}
