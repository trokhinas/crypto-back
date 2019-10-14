package vsu.labs.crypto.entity.test;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;
import java.util.Set;


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

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "question")
    private List<AnswerEntity> answerList;

    @OneToMany(mappedBy = "question")
    private List<TaskEntity> taskList;

}
